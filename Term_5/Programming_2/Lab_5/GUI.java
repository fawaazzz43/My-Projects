
package lab5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;



public class GUI extends javax.swing.JFrame {

    private JTextField jTextField1ActionPerformed;
    private  String text;
    private int Id;
    private String name ;
    private int age ;
    private String Gender ;
    private String Department ;
    private float GPA ;
    StudentDatabase data= new StudentDatabase("student.txt");
        Student s;
        Student sup;
    //StudentDatabase sdb = new StudentDatabase() ;

    /**
     * Creates new form GUI
     */
    public GUI() 
    {
        initComponents();
        try {
            data.readFromFile();
            sdb.readFromFile();
            fillTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
        }
        Home.setVisible(true);
        add.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        updateform.setVisible(false);
    }

  StudentDatabase sdb = new StudentDatabase("student.txt") ;


    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        add = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxadd = new javax.swing.JComboBox<>();
        jTextFieldadd1 = new javax.swing.JTextField();
        jTextFieldadd2 = new javax.swing.JTextField();
        jButtonadd1 = new javax.swing.JButton();
        jButton7add2 = new javax.swing.JButton();
        delete = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabledel = new javax.swing.JTable();
        jButtondel = new javax.swing.JButton();
        jButtondel2 = new javax.swing.JButton();
        update = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableup = new javax.swing.JTable();
        jButtonup = new javax.swing.JButton();
        updateform = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxform = new javax.swing.JComboBox<>();
        jTextFieldform1 = new javax.swing.JTextField();
        jTextFieldform2 = new javax.swing.JTextField();
        jButtonform1 = new javax.swing.JButton();
        jButtonform2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student System Mangement");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Home.setAlignmentX(0.0F);
        Home.setAlignmentY(0.0F);
        Home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Welcome To Our Student System Mangement");
        Home.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 8, 431, -1));

        Add.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Add.setText("ADD STUDENT");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        Home.add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 62, -1, -1));

        jButton2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton2.setText("UPDATE STUDENT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Home.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 121, -1, -1));

        jButton4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton4.setText("DELETE STUDENT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Home.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 189, -1, 23));

        jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton1.setText("search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Home.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 248, -1, -1));

        jButton3.setText("View");
        Home.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 311, -1, -1));

        getContentPane().add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 11, 790, 470));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel2.setText("Add Sudent");

        jLabel3.setText("You must choose option from combo box then enter your valid input then enter ok");

        jComboBoxadd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student ID", "Full Name", "Age", "Gender", "Department", "GPA" }));
        jComboBoxadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxaddActionPerformed(evt);
            }
        });

        jTextFieldadd1.setEditable(false);
        jTextFieldadd1.setText("Chose from combo box");
        jTextFieldadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldadd1ActionPerformed(evt);
            }
        });

        jTextFieldadd2.setEditable(false);
        jTextFieldadd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldadd2ActionPerformed(evt);
            }
        });

        jButtonadd1.setText("ok");
        jButtonadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonadd1ActionPerformed(evt);
            }
        });

        jButton7add2.setText("finish");
        jButton7add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7add2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jComboBoxadd, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addLayout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(jTextFieldadd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addLayout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addComponent(jButtonadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jButton7add2))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jTextFieldadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxadd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                        .addComponent(jTextFieldadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addComponent(jButtonadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton7add2)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 799, 560));
        add.getAccessibleContext().setAccessibleName("");

        jLabel4.setText("Delete Student");

        jTabledel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "num", "Student ID", "Full Name ", "Age", "Gender", "Department", "GPA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabledel.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTabledel);

        jButtondel.setText("Delete");
        jButtondel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondelActionPerformed(evt);
            }
        });

        jButtondel2.setText("Finish");
        jButtondel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteLayout = new javax.swing.GroupLayout(delete);
        delete.setLayout(deleteLayout);
        deleteLayout.setHorizontalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteLayout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jLabel4))
                    .addGroup(deleteLayout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(jButtondel2))
                    .addGroup(deleteLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(deleteLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jButtondel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        deleteLayout.setVerticalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButtondel)
                .addGap(39, 39, 39)
                .addComponent(jButtondel2)
                .addGap(63, 63, 63))
        );

        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 799, 481));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel5.setText("Update");

        jTableup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "num", "Student ID", "Full Name ", "Age", "Gender", "Department", "GPA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableup.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jTableup);

        jButtonup.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jButtonup.setText("update");
        jButtonup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update);
        update.setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateLayout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(jLabel5))
                    .addGroup(updateLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jButtonup)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jButtonup)
                .addGap(46, 46, 46))
        );

        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 799, 481));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        jLabel6.setText("Fill The Form");

        jComboBoxform.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student ID", "Full Name", "Age", "Gender", "Department", "GPA" }));
        jComboBoxform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxformActionPerformed(evt);
            }
        });

        jTextFieldform1.setEditable(false);
        jTextFieldform1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldform1ActionPerformed(evt);
            }
        });

        jTextFieldform2.setEditable(false);
        jTextFieldform2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldform2ActionPerformed(evt);
            }
        });

        jButtonform1.setText("ok");
        jButtonform1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonform1ActionPerformed(evt);
            }
        });

        jButtonform2.setText("finish");
        jButtonform2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonform2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateformLayout = new javax.swing.GroupLayout(updateform);
        updateform.setLayout(updateformLayout);
        updateformLayout.setHorizontalGroup(
            updateformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateformLayout.createSequentialGroup()
                .addGroup(updateformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateformLayout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateformLayout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jComboBoxform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateformLayout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addGroup(updateformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonform2)
                            .addComponent(jButtonform1)))
                    .addGroup(updateformLayout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jTextFieldform1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateformLayout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jTextFieldform2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        updateformLayout.setVerticalGroup(
            updateformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateformLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(24, 24, 24)
                .addComponent(jComboBoxform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jTextFieldform1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jTextFieldform2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButtonform1)
                .addGap(62, 62, 62)
                .addComponent(jButtonform2)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        getContentPane().add(updateform, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 799, 530));

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 83, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "num", "Student ID", "Full Name ", "Age", "Gender", "Department", "GPA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 252, 94));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // clear existing rows

        int counter = 1;
        for (Student st : sdb.ReturnAllStudents()) {
            Object[] row = {
                    counter++,
                    st.getStudentID(),
                    st.getFullName(),
                    st.getAge(),
                    st.getGender(),
                    st.getDepartment(),
                    st.getGPA()
            };
            model.addRow(row);
        }
    }

    public static boolean isValid(String id)
    {
        return id.matches("\\d{4,5}");
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String searchForId = jTextField1.getText().trim();
        search(searchForId,true);
    }//GEN-LAST:event_jButton1ActionPerformed
    public int search (String searchForId,boolean flag) {


        if (isValid(searchForId))
        {
            Student foundStudent = sdb.SearchStudent(Integer.parseInt(searchForId));

            if (foundStudent != null)
            {
                int rowIndex = -1;

                for (int i = 0; i < jTable1.getRowCount(); i++)
                {
                    String tableId = jTable1.getValueAt(i, 1).toString();
                    if (tableId.equals(String.valueOf(foundStudent.getStudentID())))
                    {
                        rowIndex = i;
                        break;
                    }
                }

                if (rowIndex != -1)
                {
                    jTable1.setRowSelectionInterval(rowIndex, rowIndex);
                    jTable1.scrollRectToVisible(jTable1.getCellRect(rowIndex, 1, true));

                if(flag){JOptionPane.showMessageDialog(this,
                    "✅ Found: " + foundStudent.getFullName(),
                    "Student Found",
                    JOptionPane.INFORMATION_MESSAGE);}
                   return 1;
                }
                else
                {
                    if(flag){
                    JOptionPane.showMessageDialog(this,
                            "❌ Student not found!",
                            "Search Result",
                            JOptionPane.WARNING_MESSAGE);}
                    return 0;
                }
            }
            else
            {
               if(flag){ JOptionPane.showMessageDialog(this,
                        "❌ Student not found!",
                        "Search Result",
                        JOptionPane.WARNING_MESSAGE);}
                return 0;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,
                    "Invalid ID format! Please enter a 4–5 digit number.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE);
            return 2;
        }}
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed

        Home.setVisible(false);
        add.setVisible(true);
        delete.setVisible(false);
        update.setVisible(false);
        updateform.setVisible(false);
    }//GEN-LAST:event_AddActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Home.setVisible(false);
        add.setVisible(false);
        delete.setVisible(false);
        update.setVisible(true);
        updateform.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) jTableup.getModel();
        model.setRowCount(0); 

        int counter = 1;
        for (Student st : data.ReturnAllStudents()) {
            Object[] row = {
                    counter++,
                    st.getStudentID(),
                    st.getFullName(),
                    st.getAge(),
                    st.getGender(),
                    st.getDepartment(),
                    st.getGPA()
            };
            model.addRow(row);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Home.setVisible(false);
        add.setVisible(false);
        delete.setVisible(true);
        update.setVisible(false);
        updateform.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) jTabledel.getModel();
        model.setRowCount(0); // clear existing rows

        int counter = 1;
        for (Student st : data.ReturnAllStudents()) {
            Object[] row = {
                    counter++,
                    st.getStudentID(),
                    st.getFullName(),
                    st.getAge(),
                    st.getGender(),
                    st.getDepartment(),
                    st.getGPA()
            };
            model.addRow(row);
        }
         
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFieldadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldadd1ActionPerformed

    }//GEN-LAST:event_jTextFieldadd1ActionPerformed

    private void jComboBoxaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxaddActionPerformed
        jTextFieldadd2.setEditable(true);
            text=(String) jComboBoxadd.getSelectedItem();
            jTextFieldadd1.setText("Enter "+text);
    }//GEN-LAST:event_jComboBoxaddActionPerformed

    private void jButtonadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonadd1ActionPerformed
                                        
    String input = jTextFieldadd2.getText().trim();
    if (input.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Please enter a value before proceeding.", 
            "Input Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        switch (text) {
            case "Student ID":
                Id = Integer.parseInt(input);
                if (Id <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "❌ D must be a positive number.", 
                        "Invalid ID", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;

            case "Full Name":
                name = input;
                if (!name.matches("[a-zA-Z ]+")) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "❌Name must contain letters only.", 
                        "Invalid Name", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;

            case "Age":
                age = Integer.parseInt(input);
                if (age < 16 || age > 100) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Age must be between 16 and 100.", 
                        "Invalid Age", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;

            case "Gender":
                if (!input.equalsIgnoreCase("male") && !input.equalsIgnoreCase("female")) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Gender must be 'Male' or 'Female'.", 
                        "Invalid Gender", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Gender = input;
                break;

            case "Department":
                Department = input;
                break;

            case "GPA":
                GPA = Float.parseFloat(input);
                if (GPA < 0.0 || GPA > 4.0) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "❌GPA must be between 0.0 and 4.0.", 
                        "Invalid GPA", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
        }
        jComboBoxadd.removeItem(jComboBoxadd.getSelectedItem());
        jTextFieldadd2.setText("");

    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Please enter a valid number for " + text, 
            "Number Format Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButtonadd1ActionPerformed

    private void jTextFieldadd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldadd2ActionPerformed

    }//GEN-LAST:event_jTextFieldadd2ActionPerformed

    private void jButton7add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7add2ActionPerformed

    if (Id == 0 || name == null || age == 0 || Gender == null || Department == null || GPA == 0.0) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Please complete all fields before finishing.", 
            "Incomplete Data", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        Student s = new Student(Id, name, age, Gender, Department, GPA);
        boolean added = data.AddStudent(s);
        data.readFromFile();

        if (added) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "✅Student added successfully!", 
                "Success", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Student with same ID already exists.", 
                "Duplicate ID", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "❌Error while saving student: " + e.getMessage(), 
            "File Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
        Home.setVisible(true);
        add.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        updateform.setVisible(false);
    }//GEN-LAST:event_jButton7add2ActionPerformed

    private void jButtondelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondelActionPerformed
        int selectedRow = jTabledel.getSelectedRow();
    if (selectedRow == -1) { 
    JOptionPane.showMessageDialog(this, "Please select a row from the table first!");
} else { 
   

        int i=jTabledel.getSelectedRow();
        int option=JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Deletion",JOptionPane.YES_NO_OPTION);
        if(option==0){
            int id = Integer.parseInt(jTabledel.getValueAt(i, 1).toString());
            data.DeleteStudent(id);
            try {
                data.readFromFile();
            } catch (Exception ex) {
                System.getLogger(GUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            Home.setVisible(false);
        add.setVisible(false);
        delete.setVisible(true);
        update.setVisible(false);
        updateform.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) jTabledel.getModel();
        model.setRowCount(0); // clear existing rows

        int counter = 1;
        for (Student st : data.ReturnAllStudents()) {
            Object[] row = {
                    counter++,
                    st.getStudentID(),
                    st.getFullName(),
                    st.getAge(),
                    st.getGender(),
                    st.getDepartment(),
                    st.getGPA()
            };
            model.addRow(row);
        }
            JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");}}
    }//GEN-LAST:event_jButtondelActionPerformed

    private void jButtondel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondel2ActionPerformed
       
        Home.setVisible(true);
        add.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        updateform.setVisible(false);
    }//GEN-LAST:event_jButtondel2ActionPerformed

    private void jComboBoxformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxformActionPerformed
        if(jComboBoxform == evt.getSource()){
            text=(String) jComboBoxform.getSelectedItem();

            jTextFieldform1.setText("Enter new "+text);
        jTextFieldform2.setEditable(true);}
    }//GEN-LAST:event_jComboBoxformActionPerformed

    private void jTextFieldform1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldform1ActionPerformed
        jTextFieldform1.setText("Chose from combo box");
    }//GEN-LAST:event_jTextFieldform1ActionPerformed

    private void jTextFieldform2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldform2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldform2ActionPerformed

    private void jButtonform1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonform1ActionPerformed

        if(sup==null)  
            sup=data.SearchStudent(Integer.parseInt(jTableup.getValueAt(jTableup.getSelectedRow() , 1).toString()));
        String input = jTextFieldform2.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a value before proceeding.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            switch (text) {
                case "Student ID":
                    Id = Integer.parseInt(input);
                    break;
                case "Full Name":
                    name = input;
                    break;
                case "Age":
                    age = Integer.parseInt(input);
                    if (age <= 0 || age > 100) {
                        JOptionPane.showMessageDialog(this,
                                "❌Age must be between 1 and 100.",
                                "Invalid Age",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
                case "Gender":
                    if (!input.equalsIgnoreCase("male") && !input.equalsIgnoreCase("female")) {
                        JOptionPane.showMessageDialog(this,
                                "Gender must be 'Male' or 'Female'.",
                                "Invalid Gender",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Gender = input;
                    break;
                case "Department":
                    Department = input;
                    break;
                case "GPA":
                    GPA = Float.parseFloat(input);
                    if (GPA < 0.0 || GPA > 4.0) {
                        JOptionPane.showMessageDialog(this,
                                "❌GPA must be between 0.0 and 4.0.",
                                "Invalid GPA",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
            }

            

            jComboBoxform.removeItem(jComboBoxform.getSelectedItem());
            jTextFieldform2.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number for " + text,
                    "Number Format Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonform1ActionPerformed

    private void jButtonform2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonform2ActionPerformed

        if(Id!=0)
        sup.setStudentID(Id);
        if(name!=null)
        sup.setFullName(name);
        if(age!=0)
        sup.setAge(age);
        if(Gender!=null)
        sup.setGender(Gender);
        if(Department!=null)
        sup.setDepartment(Department);
        if(GPA!=0)
        sup.setGPA(GPA);
        try {
            data.saveToFile();
            data.readFromFile();
        } catch (Exception ex) {
            System.getLogger(GUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }    
        sup=null;
        JOptionPane.showMessageDialog(this,
                    "✅Student updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
          Home.setVisible(true);
        add.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        updateform.setVisible(false);
    }//GEN-LAST:event_jButtonform2ActionPerformed

    private void jButtonupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonupActionPerformed
        int selectedRow = jTableup.getSelectedRow();
        if (selectedRow == -1) { 
    JOptionPane.showMessageDialog(this, "Please select a row from the table first!");
} else { 
        int i=jTableup.getSelectedRow();
        int option=JOptionPane.showConfirmDialog(this, "Are you sure you want to Update this student?", "Update",JOptionPane.YES_NO_OPTION);
        if(option==0){
        Id=0;
        name = null;
        age =0;
        Gender =null;
        Department =null;
        GPA =0;
        Home.setVisible(false);
        add.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        updateform.setVisible(true);
        }}
    }//GEN-LAST:event_jButtonupActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel add;
    private javax.swing.JPanel delete;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7add2;
    private javax.swing.JButton jButtonadd1;
    private javax.swing.JButton jButtondel;
    private javax.swing.JButton jButtondel2;
    private javax.swing.JButton jButtonform1;
    private javax.swing.JButton jButtonform2;
    private javax.swing.JButton jButtonup;
    private javax.swing.JComboBox<String> jComboBoxadd;
    private javax.swing.JComboBox<String> jComboBoxform;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTabledel;
    private javax.swing.JTable jTableup;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldadd1;
    private javax.swing.JTextField jTextFieldadd2;
    private javax.swing.JTextField jTextFieldform1;
    private javax.swing.JTextField jTextFieldform2;
    private javax.swing.JPanel update;
    private javax.swing.JPanel updateform;
    // End of variables declaration//GEN-END:variables
}
