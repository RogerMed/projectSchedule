package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainForm  extends JFrame {
    private JPanel rootPanel;
    private JButton ButtonNewContact;
    private JButton ButtonRemove;
    private JTable tableContacts;
    private JLabel LabelContactCount;


    private ContactBusiness mContactBusiness;
    private  String mName  = "";
    private  String mPhone = "";



    public MainForm(){
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        // DIMENCIONA NO MEIO DA TELA
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/ 2 -getSize().width / 2,dim.height/ 2 - getSize().height / 2);


        //QUANDO VC CLICA NO BOTÃO DE FEHCAR ELE PARA DE RODAR O PROGAMA
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
        LoadContacts();
    }

    private void LoadContacts(){
       List<ContactEntity> contactEntityList =  mContactBusiness.getList();

       String[] columnNames = {"Nome", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object [0][0],columnNames);

        for (ContactEntity i : contactEntityList ){
            Object[] o = new Object[2];
            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }
          tableContacts.clearSelection();
          tableContacts.setModel(model);

          LabelContactCount.setText(mContactBusiness.getContactCountDescription());

    }


    private void setListeners(){
        //AÇÃO DO BOTAO NOVO CONTATO
        ButtonNewContact.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
        //Chama o ContractForm Para preencher o nome e o telefone
                new ContractForm();
                dispose();
            }
        });


        //PEGA O CLIQUE DE UMA LINHA DA TABELA PARA SABER QUAL É O CARA Q VC CLICOU
        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){

                    if(tableContacts.getSelectedRow() != -1){
                   mName =  tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                   mPhone =  tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                }
              }
            }
        });

        //AÇÃO DO BOTÃO  REMOVER
        ButtonRemove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mContactBusiness.delete(mName, mPhone);
                    LoadContacts();

                    mName = "";
                    mPhone = "";
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(new JFrame(), ex.getMessage());

                }
            }
        });

    }
}
