
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class MainScreen extends javax.swing.JFrame {

   
    public MainScreen() 
	{
        initComponents();
    }

    private void initComponents() 
	{
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        charList = new javax.swing.JList();
        tName = new javax.swing.JLabel();
        tWin = new javax.swing.JLabel();
        tLoss = new javax.swing.JLabel();
        tDraw = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        charInfo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        charHp = new javax.swing.JLabel();
        charClass = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        criarChar = new javax.swing.JPanel();
        perso3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        perso3name = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        perso3race = new javax.swing.JComboBox();
        perso2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        perso2name = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        perso2race = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        nomeTime = new javax.swing.JTextField();
        perso1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        perso1name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        perso1race = new javax.swing.JComboBox();
        painelLimpo = new javax.swing.JPanel();
        fightResults = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        inventory = new javax.swing.JPanel();
        invName = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        invData = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(750, 550));
        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() 
		{
            public void windowOpened(java.awt.event.WindowEvent evt) 
			{
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 170));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 170));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 170));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Meu time");

        charList.setModel(new javax.swing.AbstractListModel() 
		{
            String[] strings = { "Char 1", "Char 2", "Char 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        charList.addListSelectionListener(new javax.swing.event.ListSelectionListener() 
		{
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
			{
                charListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(charList);

        tName.setText("Nome: ");

        tWin.setText("Vitórias: ");

        tLoss.setText("Derrotas: ");

        tDraw.setText("Empates:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tWin))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tLoss))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tDraw)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(tName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tWin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tLoss)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tDraw)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.setText("Criar time");
        jButton1.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Procurar batalha");
        jButton3.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Inventário");
        jButton4.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Informações de ");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        charHp.setText("HP: ");

        charClass.setText("Classe: ");

        javax.swing.GroupLayout charInfoLayout = new javax.swing.GroupLayout(charInfo);
        charInfo.setLayout(charInfoLayout);
        charInfoLayout.setHorizontalGroup(
            charInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(charInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(charInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addGroup(charInfoLayout.createSequentialGroup()
                        .addGroup(charInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(charHp)
                            .addComponent(charClass))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        charInfoLayout.setVerticalGroup(
            charInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(charInfoLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(charHp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(charClass)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        content.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        content.setMaximumSize(new java.awt.Dimension(524, 465));
        content.setMinimumSize(new java.awt.Dimension(524, 465));
        content.setLayout(new java.awt.CardLayout());

        criarChar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        criarChar.setMaximumSize(new java.awt.Dimension(502, 441));
        criarChar.setMinimumSize(new java.awt.Dimension(502, 441));

        perso3.setBorder(javax.swing.BorderFactory.createTitledBorder("Personagem 3"));
        perso3.setMaximumSize(new java.awt.Dimension(228, 112));
        perso3.setMinimumSize(new java.awt.Dimension(228, 112));

        jLabel11.setText("Nome do personagem:");

        jLabel12.setText("Classe do personagem:");

        perso3race.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Knight", "Thief", "Wizard" }));

        javax.swing.GroupLayout perso3Layout = new javax.swing.GroupLayout(perso3);
        perso3.setLayout(perso3Layout);
        perso3Layout.setHorizontalGroup(
            perso3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perso3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(perso3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(perso3name)
                    .addComponent(jLabel12)
                    .addComponent(perso3race, 0, 196, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        perso3Layout.setVerticalGroup(
            perso3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perso3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(perso3name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(perso3race, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        perso2.setBorder(javax.swing.BorderFactory.createTitledBorder("Personagem 2"));
        perso2.setMaximumSize(new java.awt.Dimension(228, 112));
        perso2.setMinimumSize(new java.awt.Dimension(228, 112));

        jLabel13.setText("Nome do personagem:");

        jLabel14.setText("Classe do personagem:");

        perso2race.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Knight", "Thief", "Wizard" }));

        javax.swing.GroupLayout perso2Layout = new javax.swing.GroupLayout(perso2);
        perso2.setLayout(perso2Layout);
        perso2Layout.setHorizontalGroup(
            perso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perso2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(perso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(perso2name)
                    .addComponent(jLabel14)
                    .addComponent(perso2race, 0, 196, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        perso2Layout.setVerticalGroup(
            perso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perso2Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(perso2name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(perso2race, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton5.setText("Criar");
        jButton5.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
                jButton5ActionPerformed(evt);
            }
        });

        jLabel15.setText("Nome do time:");

        nomeTime.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
                nomeTimeActionPerformed(evt);
            }
        });

        perso1.setBorder(javax.swing.BorderFactory.createTitledBorder("Personagem 1"));
        perso1.setMaximumSize(new java.awt.Dimension(228, 112));
        perso1.setMinimumSize(new java.awt.Dimension(228, 112));

        jLabel5.setText("Nome do personagem:");

        jLabel6.setText("Classe do personagem:");

        perso1race.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Knight", "Thief", "Wizard" }));

        javax.swing.GroupLayout perso1Layout = new javax.swing.GroupLayout(perso1);
        perso1.setLayout(perso1Layout);
        perso1Layout.setHorizontalGroup(
            perso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perso1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(perso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(perso1name)
                    .addComponent(jLabel6)
                    .addComponent(perso1race, 0, 196, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        perso1Layout.setVerticalGroup(
            perso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perso1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(perso1name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(perso1race, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout criarCharLayout = new javax.swing.GroupLayout(criarChar);
        criarChar.setLayout(criarCharLayout);
        criarCharLayout.setHorizontalGroup(
            criarCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarCharLayout.createSequentialGroup()
                .addContainerGap(385, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(226, 226, 226))
            .addGroup(criarCharLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeTime, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(criarCharLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(criarCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perso3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(perso2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(criarCharLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(perso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        criarCharLayout.setVerticalGroup(
            criarCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarCharLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(criarCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(nomeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(perso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(perso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(perso3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5))
        );

        content.add(criarChar, "card2");

        painelLimpo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout painelLimpoLayout = new javax.swing.GroupLayout(painelLimpo);
        painelLimpo.setLayout(painelLimpoLayout);
        painelLimpoLayout.setHorizontalGroup(
            painelLimpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        painelLimpoLayout.setVerticalGroup(
            painelLimpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        content.add(painelLimpo, "card2");

        jLabel3.setText("Resultados da batalha");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout fightResultsLayout = new javax.swing.GroupLayout(fightResults);
        fightResults.setLayout(fightResultsLayout);
        fightResultsLayout.setHorizontalGroup(
            fightResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fightResultsLayout.createSequentialGroup()
                .addContainerGap(337, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(224, 224, 224))
            .addGroup(fightResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        fightResultsLayout.setVerticalGroup(
            fightResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fightResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );

        content.add(fightResults, "card4");

        invName.setText("Inventário de Ninguém");

        invData.setEditable(false);
        invData.setColumns(20);
        invData.setRows(5);
        invData.setText("<--- Clique nos personagens ao lado para ver o inventário.");
        jScrollPane3.setViewportView(invData);

        javax.swing.GroupLayout inventoryLayout = new javax.swing.GroupLayout(inventory);
        inventory.setLayout(inventoryLayout);
        inventoryLayout.setHorizontalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(inventoryLayout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(invName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        inventoryLayout.setVerticalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(invName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        content.add(inventory, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(charInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addGap(27, 27, 27)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(charInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    public static MainScreen currentFrame = null;
    public void setCenterScreen()
    {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) 
	{
        content.removeAll();
        content.add(criarChar);
        content.repaint(); 
        content.revalidate();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) 
	{
        SocketManager.mainSocket.sendData("BA");
        content.removeAll();
        content.add(painelLimpo);
        content.repaint();        
        content.revalidate();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) 
	{
        if(perso1name.getText().equals("") || perso2name.getText().equals("")|| perso3name.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Você deve preencher o nome dos 3 personagens.", "Erro ao criar time", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(nomeTime.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Você deve preencher o nome do time.", "Erro ao criar time", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SocketManager.mainSocket.sendData("BB"+nomeTime.getText()+"|"+perso1name.getText()+","+perso1race.getSelectedItem().toString()+";"+perso2name.getText()+","+perso2race.getSelectedItem().toString()+";"+perso3name.getText()+","+perso3race.getSelectedItem().toString());
    }

    private void nomeTimeActionPerformed(java.awt.event.ActionEvent evt) 
	{
        
    }

    private void charListValueChanged(javax.swing.event.ListSelectionEvent evt) 
	{
        if (!evt.getValueIsAdjusting())             
        {
            if(charList.getSelectedIndex() >= 0)
            {
                String name = charList.getSelectedValue().toString();
                boolean inv = false;
                for(int i = 0; i < content.getComponents().length; i++)
                {
                    if(content.getComponents()[i] == inventory)
                        inv = true;                        
                }
                if(inv)
                    SocketManager.mainSocket.sendData("DA"+name);                
                SocketManager.mainSocket.sendData("BC"+name);
            }
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) 
	{
       if(jButton3.getText().equals("Procurar batalha"))       
           SocketManager.mainSocket.sendData("CA1");
       else
           SocketManager.mainSocket.sendData("CA0");
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) 
	{	
        content.removeAll();
        content.add(inventory);
        content.repaint();        
        content.revalidate();
    }
    
    public javax.swing.JLabel charClass;
    public javax.swing.JLabel charHp;
    public javax.swing.JPanel charInfo;
    public javax.swing.JList charList;
    public javax.swing.JPanel content;
    private javax.swing.JPanel criarChar;
    public javax.swing.JPanel fightResults;
    public javax.swing.JTextArea invData;
    public javax.swing.JLabel invName;
    private javax.swing.JPanel inventory;
    private javax.swing.JButton jButton1;
    public javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nomeTime;
    private javax.swing.JPanel painelLimpo;
    private javax.swing.JPanel perso1;
    private javax.swing.JTextField perso1name;
    private javax.swing.JComboBox perso1race;
    private javax.swing.JPanel perso2;
    private javax.swing.JTextField perso2name;
    private javax.swing.JComboBox perso2race;
    private javax.swing.JPanel perso3;
    private javax.swing.JTextField perso3name;
    private javax.swing.JComboBox perso3race;
    public javax.swing.JLabel tDraw;
    public javax.swing.JLabel tLoss;
    public javax.swing.JLabel tName;
    public javax.swing.JLabel tWin;    
}
