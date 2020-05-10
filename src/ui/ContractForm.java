package ui;

import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContractForm  extends JFrame {
    private JPanel rootPanel;
    private JTextField textName;
    private JTextField textPhone;
    private JButton buttonCancel;
    private JButton ButtonSave;


    private ContactBusiness mContactBusiness;

    public ContractForm(){
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
    }

    private void setListeners(){
        ButtonSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
             try{
                 String name = textName.getText();
                 String phone = textPhone.getText();

                 mContactBusiness.save(name, phone);


                 new MainForm();
                 dispose();

            } catch(Exception ex){
                 JOptionPane.showConfirmDialog(new JFrame(), ex.getMessage());

             }
            }
        });



        buttonCancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });


    }
}
