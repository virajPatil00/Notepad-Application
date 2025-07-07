package Notepad;

import java.awt.event.*;//1
import java.awt.*;//3
import javax.swing.*;//2
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.*;

import java.io.*;//4
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NotepadDemo extends JFrame implements ActionListener {
    JMenuBar mb;

    String strft,strfullfont;
    int intsizeval,intstyl,intcolr,intbgcolr,theamval;

    JMenu file,edit,format,view;
    JMenuItem nw,op,sa,cl,cp,cut,pt,f1,f2,font,clr,clrscrn,bgcolr,datetime;
    JCheckBoxMenuItem pr;
    JTextArea ta;
    Choice ch;
    int cpytxt;
    String st,gtcur;
    FontDemo1 fd = new FontDemo1(this);
    Color col,bgcol;
    NotepadDemo()

    {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        mb = new JMenuBar();
         ta=new JTextArea();
        c.add(new JScrollPane(ta),BorderLayout.CENTER);  
        c.add("North",mb);


        strft="Arial";
        intstyl=0;
        intsizeval=14;
        intcolr=-16777216;
        intbgcolr=-1;


        strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr+"\n"+intbgcolr;
      


        file= new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        view= new JMenu("View");
        mb.add(file);
        mb.add(edit);
        mb.add(format);
        mb.add(view);
       

        nw=new JMenuItem("New");
        op=new JMenuItem("Open");
        sa=new JMenuItem("Save");
        // cl=new JMenuItem("Close");
        cp=new JMenuItem("Copy");
        pt=new JMenuItem("Paste");
        cut=new JMenuItem("Cut");
        datetime=new JMenuItem("Date/Time");


        file.add(nw);
        file.add(op);
        file.add(sa);
        // file.add(cl);
        edit.add(cp);
        edit.add(pt);
        edit.add(cut);
        // edit.addSeparator();
        edit.add(datetime);

        // cl.setEnabled(false);
        // pr = new JCheckBoxMenuItem("Print");
        // file.add(pr);
        // file.addSeparator();

        font = new JMenuItem("Font");
        clr=new JMenuItem("Color");
        bgcolr=new JMenuItem("BG Color");
        format.add(font);
        format.add(clr);
        format.add(bgcolr);


        cl=new JMenuItem("Close");
        clrscrn=new JMenuItem("Clear Screen");
        view.add(cl);
        view.add(clrscrn);

        nw.addActionListener(this);
        op.addActionListener(this);
        sa.addActionListener(this);
        cl.addActionListener(this);
        cp.addActionListener(this);
        pt.addActionListener(this);
        cut.addActionListener(this);
        datetime.addActionListener(this);
        clrscrn.addActionListener(this);
        font.addActionListener(this);
        clr.addActionListener(this);
        bgcolr.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(nw.isArmed())
            this.newFile();

        if(op.isArmed())
            this.openFile();

        if(sa.isArmed())
            try{
                this.saveFile();
            }catch(Exception ae){}
        // if(cl.isArmed())
        //     System.out.println("Close is Selected");

        if(clr.isArmed())
            colourchoice();

        if(bgcolr.isArmed())
            backcolor();

        if(font.isArmed())
        {
            fd.setVisible(true);
            fd.setSize(445,400);
            fd.setResizable(false);
            fd.setTitle("Select Font");      
        }

        if(cp.isArmed())
        {
            //  cpytxt =ta.getSelectedText();
            //  gtcur=ta.getSelectedText();
            ta.copy();
            cpytxt=1;
        }

        if(pt.isArmed())
        {
            if(cpytxt==1)
            {
            // Cursor css=Cursor.
            // ta.insert(gtcur,Cursor.CUSTOM_CURSOR);
            ta.paste();
            }
            if(cpytxt==2)
            {
                ta.paste();
                cpytxt=0;
            }
        }
        
        if(cut.isArmed())
        {
        ta.cut();
        cpytxt=2;
        }

        if(datetime.isArmed())
        this.printDateTime();

        if(cl.isArmed())
        {
            this.closeWindow();
        }

        if(clrscrn.isArmed())
        {
            this.clearScreen();
        }
  
    }

    //----------------------------------- THEAM VALUE ----------------------------------------------

    void getTeamValue(int value)
    {
        theamval=value;
        if(theamval%2==0)
        {
        intbgcolr=-1;
        strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr+"\n"+intbgcolr;
        System.out.println("THEAM VALUE ================+++++++++++++++++  "+intbgcolr);
        }
        if(theamval%2!=0)
        {
        // intbgcolr=-13421773;
        intbgcolr=-1;
        strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr+"\n"+intbgcolr;
        System.out.println("THEAM VALUE ================+++++++++++++++++  "+intbgcolr);
        }
        
    }

    void printDateTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");  
        LocalDateTime now = LocalDateTime.now();     
        System.out.println(dtf.format(now));  
        ta.append(dtf.format(now));
    }

    //----------------------------------- SETTING FILE ----------------------------------------------

    void getFontValue(String a,int b,int c)
    {

        strft=a;
        intstyl=b;
        intsizeval=c;
        

        strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr+"\n"+intbgcolr;
    }

    //---------------------------------OPEN FILE------------------------------------

    void openFile()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
        int i = fc.showOpenDialog(this);
        if(i == JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile();

            String fname = f.getPath();
            String set_fname=fname;
            String st1="";
            String st2="";
            try{
                BufferedReader br = new BufferedReader(new FileReader(fname));
                System.out.println(fname);
                while((st1=br.readLine())!=null)
                    st2+=st1+"\n";
                ta.setText(st2);
                br.close();
            }catch(Exception e){}

            //------------------------------OPEN SETTING FILE---------------------------


            try{
                String setstr1="";
                int cnt=0;
                String getfntval="Arial";
                int getstylval=0;
                int getsizeval=14;
  
                //creating a constructor of StringBuffer class  
                StringBuffer sb= new StringBuffer(set_fname);  
                //invoking the method  
                sb.deleteCharAt(sb.length()-1);  
                sb.deleteCharAt(sb.length()-1);  
                sb.deleteCharAt(sb.length()-1);  
                sb.deleteCharAt(sb.length()-1);  


                BufferedReader brset = new BufferedReader(new FileReader(sb+"_SET.txt"));
                // System.out.println("NAME ===== "+sb);
                while((setstr1=brset.readLine())!=null)
                {
                    cnt++;

                    System.out.println(setstr1);
                    if(cnt==1)  getfntval=setstr1;
                    if(cnt==2)  getstylval=Integer.parseInt(setstr1);
                    if(cnt==3)  getsizeval=Integer.parseInt(setstr1);  
                    if(cnt==4)  intcolr=Integer.parseInt(setstr1);
                    if(theamval%2==0)
                    if(cnt==5)  intbgcolr=Integer.parseInt(setstr1);

                    // this.getfntval_from_set(cnt,setstr1);
                }

                Font ft = new Font(getfntval, getstylval, getsizeval);
                ta.setFont(ft);

                // col=Color.decode(Integer.toString(intcolr));
                col=new Color(intcolr);
                ta.setForeground(col);

                bgcol=new Color(intbgcolr);
                ta.setBackground(bgcol);
                
                brset.close();
            }catch(Exception e){}
        }
    }

    //-----------------------------------------SAVE FILE---------------------------------

    void saveFile()throws Exception
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
        fc.setDialogTitle("Specify a file to save");

        int userSelection = fc.showSaveDialog(this);

        String str;
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            str=ta.getText();
            File fileToSave = fc.getSelectedFile();
            File setfilesave=fileToSave;
            FileWriter fw = new FileWriter(fileToSave+".txt");
            for(int i=0;i<str.length();i++)
             fw.write(str.charAt(i));
            fw.close();

            //-------------------SETTING FILE SAVE-------------------
            
            // System.out.println("Answer ====  "+strfullfont);

            //IF BARK THEAM THEN DON'T SAVE BGCOLOR 
            // if(val%2!=0)
            // {
            //      strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr+"\n"+intbgcolr;
            // }
            FileWriter setfile=new FileWriter(setfilesave+"_SET.txt");
            for(int j=0;j<strfullfont.length();j++)
            {
            setfile.write(strfullfont.charAt(j));
            }
            setfile.close();

            //------------------------------------------------
            
        }
    }

    //------------------------------------NEW FILE OF NOTEPAD-----------------------------------
    void newFile()
    {
        Notepad np = new Notepad();
        np.setTitle("MY NotePad");
        np.setSize(600,600);
        np.setVisible(true);
        np.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    //----------------------------CLEAR SCREEN----------------------------
    void clearScreen()
    {
        ta.setText(null);
    }

    //-------------------------CLOSE WINDOW-----------------------------
    void closeWindow()
    {
        System.exit(1);
    }

    //-----------------------------COLOR----------------------------------

    void colourchoice()
    {
        // JColorChooser cc= new JColorChooser();
        col=JColorChooser.showDialog(this,"Select a color",Color.black);
        intcolr=col.getRGB();
        

        strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr;

        ta.setForeground(col);    
    }

    //-------------------------------------BACKGROUND COLOR------------------------------------
    void backcolor()
    {
        bgcol=JColorChooser.showDialog(this, "Select BackGround Color", Color.gray);
        intbgcolr=bgcol.getRGB();
        System.out.println("BACKGROUND COLOR ============= "+intbgcolr);
        ta.setBackground(bgcol);

        strfullfont=strft+"\n"+Integer.toString(intstyl)+"\n"+Integer.toString(intsizeval)+"\n"+intcolr+"\n"+intbgcolr;
    }

    public static void main(String[] args) {
        HomeScreen hm= new HomeScreen();
        hm.setTitle("HomeScreen");
        hm.setSize(500, 500);
        hm.setResizable(false);
        hm.setVisible(true);
    }
}


//-----------------------------------------------------------------------------//
// /------------------------------------THE END------------------------------/ //
//-----------------------------------------------------------------------------//


class FontDemo1 extends JFrame implements ActionListener{
    JComboBox cb,fntstylCb,sizeCb;
    JLabel lbl;
    String strfnt,strgetstyle,strgetsize;
    JButton btnok,btncan;
    int bln=0,intsize;
    NotepadDemo np;
    FontDemo1(NotepadDemo n)
    {
        np=n;
        Container c = getContentPane();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String ft[]=ge.getAvailableFontFamilyNames();
        c.setLayout(null);
        btnok = new JButton("OK");
        btncan=new JButton("Cancel");
        cb = new JComboBox<>();
        fntstylCb=new JComboBox<>();
        sizeCb=new JComboBox<>();
        lbl= new JLabel("Font : ");


        for(int i =0;i<ft.length;i++)
        {
            cb.addItem(ft[i]);
        }


        String strstyl[]={"Regular","Italic","Bold","Bold Italic"};
        for(int i=0;i<strstyl.length;i++)
        {
        fntstylCb.addItem(strstyl[i]);
        }

        String strsize[]={"8","9","10","14","16","18","20","22","24","28","36","48","56","72"};
        for(int i=0;i<strsize.length;i++)
        {
            sizeCb.addItem(strsize[i]);
        }


        cb.setBounds(20,70,150,20);
        fntstylCb.setBounds(190, 70, 110, 20);
        sizeCb.setBounds(320, 70, 75, 20);
        lbl.setBounds(50,50,100,20);
        btnok.setBounds(215,320,100,30);
        btncan.setBounds(320, 320, 100, 30);
        sizeCb.setSelectedItem("14");
        c.add(cb);
        c.add(fntstylCb);
        c.add(sizeCb);
        c.add(lbl);
        c.add(btnok);
        c.add(btncan);
        btnok.addActionListener(this);
        btncan.addActionListener(this);
    }

    void setsizevalue(String sizeValue)
    {
        switch(sizeValue)
        {
            case"8":
            intsize=8;
            break;
            
            case"9":
            intsize=9;
            break;
                        
            case"10":
            intsize=10;
            break;
                        
            case"14":
            intsize=14;
            break;
                        
            case"16":
            intsize=16;
            break;
                        
            case"18":
            intsize=18;
            break;
                        
            case"20":
            intsize=20;
            break;
                        
            case"22":
            intsize=22;
            break;
                        
            case"24":
            intsize=24;
            break;
                        
            case"28":
            intsize=28;
            break;
                        
            case"36":
            intsize=36;
            break;
                        
            case"48":
            intsize=48;
            break;
                        
            case"56":
            intsize=56;
            break;
                        
            case"72":
            intsize=72;
            break;


        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnok.getModel().isArmed())
        {

        strfnt=(String)cb.getSelectedItem();
        strgetstyle=(String)fntstylCb.getSelectedItem();
        strgetsize=(String)sizeCb.getSelectedItem();
        // System.out.println("Size = "+strgetsize);
        this.setsizevalue(strgetsize);
        if(strgetstyle=="Regular")
        {
        Font f = new Font(strfnt,Font.PLAIN,intsize);
        np.ta.setFont(f); 
        np.getFontValue(strfnt, 0, intsize);
        }
        else if(strgetstyle=="Bold")
        {
        Font f = new Font(strfnt,Font.BOLD,intsize);
        np.ta.setFont(f); 
          np.getFontValue(strfnt, 1, intsize);
        }
        else if(strgetstyle=="Italic")
        {
        Font f = new Font(strfnt,Font.ITALIC,intsize);
        np.ta.setFont(f); 
          np.getFontValue(strfnt, 2, intsize);
        }
        else if(strgetstyle=="Bold Italic")
        {
        Font f = new Font(strfnt,Font.BOLD+Font.ITALIC,intsize);
        np.ta.setFont(f);  
          np.getFontValue(strfnt, 3, intsize); 
        }
        else
        {
        Font f = new Font(strfnt,Font.BOLD+Font.PLAIN,intsize);
        np.ta.setFont(f);
        }

        }


        if(btncan.getModel().isArmed())
        {
            this.setVisible(false);
        }
    
}
}


//---------------------------------------------------------------------------------------------------
//                                          HOME SCREEN
//---------------------------------------------------------------------------------------------------


class HomeScreen extends JFrame implements ActionListener{
    Button nw,opn,tmp;
    JButton theam;
    JLabel nwlbl,opnlbl,tmplbl;
    int cntdone,theamcnt;
    Container c;

    NotepadDemo np = new NotepadDemo();
    Template tmpt= new Template(np);

    HomeScreen()
    {
        c = getContentPane();
        c.setLayout(null);

        theamcnt=0;
        ImageIcon day=new ImageIcon("Project/40w/Asset 6.png");
        theam=new JButton(day);
        theam.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 2, new Color(70, 70,70)));
        nw=new Button("NEW");
        opn=new Button("OPEN");
        tmp=new Button("TEMPLATE");
        nwlbl=new JLabel("Open a NEW File");
        opnlbl=new JLabel("Opena a Pre-text");
        tmplbl=new JLabel("Select a Template");


        theam.setBounds(436, 3, 45, 45);
        nw.setBounds(100, 100, 100, 50);
        opn.setBounds(100, 200, 100, 50);
        tmp.setBounds(100, 300, 100, 50);
        nwlbl.setBounds(250, 100, 200, 50);
        opnlbl.setBounds(250, 200, 200, 50);
        tmplbl.setBounds(250, 300, 200, 50);
     

        c.add(theam);
        c.add(nw);
        c.add(opn);
        c.add(tmp);
        c.add(nwlbl);
        c.add(opnlbl);
        c.add(tmplbl);


        nw.addActionListener(this);
        opn.addActionListener(this);
        tmp.addActionListener(this);
        theam.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==nw)
        this.NewWindow();

        if(e.getSource()==opn)
        this.OpenWindow();

        if(e.getSource()==tmp)
        this.TemplateWindow();

        if(e.getSource()==theam)
        {
        theamcnt++;
        this.setTheam(theamcnt);
        }
            
    }

    //set new window of the notepad application 
    void NewWindow()
    {
      
        np.setTitle("MY NotePad");
        np.setSize(800,700);
        np.setVisible(true);
        this.setVisible(false);
    }


    //open
    void OpenWindow()
    {
    JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
        int i = fc.showOpenDialog(this);
    
        if(i == JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile();

            String fname = f.getPath();
            String st1="";
            String st2="";
            try{
                BufferedReader br = new BufferedReader(new FileReader(fname));
                while((st1=br.readLine())!=null)
                    st2+=st1+"\n";
                np.ta.setText(st2);
                cntdone=1;
                br.close();
            }catch(Exception e){}
        }
        if(cntdone==1)
        {
        np.setTitle("MY NotePad");
        np.setSize(800,700);
        np.setVisible(true);
        this.setVisible(false);
        }
    }


    void TemplateWindow()
    {
      
        tmpt.setTitle("MY NotePad");
        tmpt.setSize(400,440);
        tmpt.setVisible(true);
        this.setVisible(false);
    }

    void setTheam(int val)
    {
        System.out.println("NUMBER ===== "+val);
        if(val%2==0)
        {
        np.getTeamValue(val);
        c.setBackground(null);

        nw.setBackground(null);
        nw.setForeground(null);
        opn.setBackground(null);
        opn.setForeground(null);
        tmp.setBackground(null);
        tmp.setForeground(null);
        theam.setBackground(null);
        theam.setForeground(null);
        ImageIcon day=new ImageIcon("Project/40w/Asset 6.png");
        theam.setIcon(day);
        theam.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 2, new Color(70, 70,70)));

        nwlbl.setForeground(null);
        opnlbl.setForeground(null);
        tmplbl.setForeground(null);

         //--------------NOTEPAD THEAM-----------

         // new Color(242, 242, 242)
         // new Color(51, 51, 51)
        np.ta.setBackground(null);
        np.ta.setForeground(null);
        np.ta.setCaretColor(null);
        // np.mb.setOpaque(true);
        np.mb.setBackground(null);

        
        np.mb.setBorder(null);
        np.ta.setBorder(null);
        
        np.file.setForeground(null);
        np.edit.setForeground(null);
        np.format.setForeground(null);
        np.view.setForeground(null);
        
        np.bgcolr.setEnabled(true);

        // nw,op,sa,cl,cp,pt,f1,f2,font,clr,clrscrn,bgcolr;
        np.nw.setBackground(null);
        np.nw.setForeground(null);
        np.op.setBackground(null);
        np.op.setForeground(null);
        np.sa.setBackground(null);
        np.sa.setForeground(null);
        np.cl.setBackground(null);
        np.cl.setForeground(null);
        np.cp.setBackground(null);
        np.cp.setForeground(null);
        np.pt.setBackground(null);
        np.pt.setForeground(null);
        np.cut.setBackground(null);
        np.cut.setForeground(null);
        np.datetime.setBackground(null);
        np.datetime.setForeground(null);
        np.datetime.setBorder(null);
        np.font.setForeground(null);
        np.font.setBackground(null);
        np.clr.setForeground(null);
        np.clr.setBackground(null);
        np.clrscrn.setForeground(null);
        np.clrscrn.setBackground(null);
        np.bgcolr.setForeground(null);
        np.bgcolr.setBackground(null);

          //--------------TEMPLATE THEAM-----------

         tmpt.c.setBackground(null);
         tmpt.letterbtn.setBackground(null);
         tmpt.letterbtn.setForeground(null);
         tmpt.emailbtn.setBackground(null);
         tmpt.emailbtn.setForeground(null);
         tmpt.noticebtn.setBackground(null);
         tmpt.noticebtn.setForeground(null);
         tmpt.blankbtn.setBackground(null);
         tmpt.blankbtn.setForeground(null);

        
        }

            //------------------------      ELSE        ------------------------------

        else 
        {
            
            // new Color(26, 26, 26)
            // new Color(51, 51, 51)
            //  new Color(31, 31, 31)
        c.setBackground(new Color(31, 31, 31));
        np.getTeamValue(val);

        nw.setBackground(new Color(51, 51, 51));
        nw.setForeground(new Color(242, 242, 242));
        opn.setBackground(new Color(51, 51, 51));
        opn.setForeground(new Color(242, 242, 242));
        tmp.setBackground(new Color(51, 51, 51));
        tmp.setForeground(new Color(242, 242, 242));
        theam.setBackground(new Color(51, 51, 51));
        theam.setForeground(new Color(242, 242, 242));

        nwlbl.setForeground(new Color(242, 242, 242));
        opnlbl.setForeground(new Color(242, 242, 242));
        tmplbl.setForeground(new Color(242, 242, 242));

        ImageIcon day=new ImageIcon("Project/40h/Asset 4.png");
        theam.setIcon(day);
        theam.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, new Color(242, 242,242)));

        //--------------NOTEPAD THEAM-----------

        np.ta.setBackground(new Color(51, 51, 51));
        np.ta.setForeground(new Color(242, 242, 242));
        np.ta.setCaretColor(new Color(242, 242, 242));
        // np.mb.setOpaque(true);
        np.mb.setBackground(new Color(41, 41, 41));


        Border bd = BorderFactory.createMatteBorder(8, 6, 8, 6,(new Color(26, 26, 26)));
        np.mb.setBorder(bd);
        np.ta.setBorder(bd);
        
        np.file.setForeground(new Color(242, 242, 242));
        np.edit.setForeground(new Color(242, 242, 242));
        np.format.setForeground(new Color(242, 242, 242));
        np.view.setForeground(new Color(242, 242, 242));
        
        np.bgcolr.setEnabled(false);

        // nw,op,sa,cl,cp,pt,f1,f2,font,clr,clrscrn,bgcolr;
        np.nw.setBackground(new Color(51, 51, 51));
        np.nw.setForeground(new Color(242, 242, 242));
        np.op.setBackground(new Color(51, 51, 51));
        np.op.setForeground(new Color(242, 242, 242));
        np.sa.setBackground(new Color(51, 51, 51));
        np.sa.setForeground(new Color(242, 242, 242));
        np.cl.setBackground(new Color(51, 51, 51));
        np.cl.setForeground(new Color(242, 242, 242));
        np.cp.setBackground(new Color(51, 51, 51));
        np.cp.setForeground(new Color(242, 242, 242));
        np.pt.setBackground(new Color(51, 51, 51));
        np.pt.setForeground(new Color(242, 242, 242));
        np.cut.setBackground(new Color(51, 51, 51));
        np.cut.setForeground(new Color(242, 242, 242));
        np.datetime.setBackground(new Color(51, 51, 51));
        np.datetime.setForeground(new Color(242, 242, 242));
        np.datetime.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(242, 242, 242)));
        np.font.setForeground(new Color(242, 242, 242));
        np.font.setBackground(new Color(51, 51, 51));
        np.clr.setForeground(new Color(242, 242, 242));
        np.clr.setBackground(new Color(51, 51, 51));
        np.clrscrn.setForeground(new Color(242, 242, 242));
        np.clrscrn.setBackground(new Color(51, 51, 51));
        np.bgcolr.setForeground(new Color(242, 242, 242));
        np.bgcolr.setBackground(new Color(51, 51, 51));

        // Color ccccc=np.ta.getBackground();
        // int iiiii=ccccc.getRGB();
        // System.out.println("TEXT AREA COLOR ==== "+iiiii);

         //--------------TEMPLATE THEAM-----------

         tmpt.c.setBackground(new Color(31, 31, 31));
         tmpt.letterbtn.setBackground(new Color(51, 51, 51));
         tmpt.letterbtn.setForeground(new Color(242, 242, 242));
         tmpt.emailbtn.setBackground(new Color(51, 51, 51));
         tmpt.emailbtn.setForeground(new Color(242, 242, 242));
         tmpt.noticebtn.setBackground(new Color(51, 51, 51));
         tmpt.noticebtn.setForeground(new Color(242, 242, 242));
         tmpt.blankbtn.setBackground(new Color(51, 51, 51));
         tmpt.blankbtn.setForeground(new Color(242, 242, 242));

        }
    }

}


//---------------------------------------------------------------------------------------------------
//                                          TEMPLATE  
//---------------------------------------------------------------------------------------------------


class Template extends JFrame implements ActionListener {
    Button letterbtn,noticebtn,emailbtn,blankbtn;
    JLabel nwlbl,opnlbl,tmplbl;
    int cntdone;
    Container c;
    NotepadDemo np;
    Template(NotepadDemo n)
    {
        np=n;
        c = getContentPane();
        c.setLayout(null);

        letterbtn=new Button("LETTER");
        noticebtn=new Button("NOTICE");
        emailbtn=new Button("EMAIL");
        blankbtn=new Button("BLANK");


        letterbtn.setBounds(50, 50, 100, 50);
        noticebtn.setBounds(50, 130, 100, 50);
        emailbtn.setBounds(50, 210, 100, 50);
        blankbtn.setBounds(50, 290, 100, 50);
     

        c.add(letterbtn);
        c.add(noticebtn);
        c.add(emailbtn);
        c.add(blankbtn);

        letterbtn.addActionListener(this);
        noticebtn.addActionListener(this);
        emailbtn.addActionListener(this);
        blankbtn.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==letterbtn)
        {
            this.letterTemplate();

        }

        if(e.getSource()==noticebtn)
        {
            this.noticeTemplate();
        }

        if(e.getSource()==emailbtn)
        {
            this.emailTemplate();
        }

        if(e.getSource()==blankbtn)
        {
            this.blankTemplate();
        }

    }
    void letterTemplate()
    {
            String fname = "F:\\Doc\\Java_Doc\\Project\\TemplateFormat\\Letter.txt";
            String st1="";
            String st2="";
            
            try{
                File f = new File(fname);
                 if(f.exists())
                {
                    System.out.println("File exits");
                }
                else
                {
                    System.out.println("File does not exits");
                }               
                BufferedReader br = new BufferedReader(new FileReader(fname));
                
                while((st1=br.readLine())!=null)
                    st2+=st1+"\n";
                    System.out.println(st2);
                np.ta.setText(st2);
                cntdone=1;
                br.close();
            }catch(Exception e){}
        
        if(cntdone==1)
        {
        np.setTitle("MY NotePad");
        np.setSize(800,700);
        np.setVisible(true);
        this.setVisible(false);
        }
    }

    void noticeTemplate()
    {
        
            String fname ="F:\\Doc\\Java_Doc\\Project\\TemplateFormat\\Letter.txt";
            String st1="";
            String st2="";
            
            try{
                File f = new File(fname);
                 if(f.exists())
                {
                    System.out.println("File exits");
                }
                else
                {
                    System.out.println("File does not exits");
                }               
                BufferedReader br = new BufferedReader(new FileReader(fname));
                
                while((st1=br.readLine())!=null)
                    st2+=st1+"\n";
                    System.out.println(st2);
                np.ta.setText(st2);
                cntdone=1;
                br.close();
            }catch(Exception e){}
        
        if(cntdone==1)
        {
        np.setTitle("MY NotePad");
        np.setSize(800,700);
        np.setVisible(true);
        this.setVisible(false);
        }
    }
    
    void emailTemplate()
    {
        
            String fname = "F:\\Doc\\Java_Doc\\Project\\TemplateFormat\\Letter.txt";
            String st1="";
            String st2="";
            
            try{
                File f = new File(fname);
                 if(f.exists())
                {
                    System.out.println("File exits");
                }
                else
                {
                    System.out.println("File does not exits");
                }               
                BufferedReader br = new BufferedReader(new FileReader(fname));
                
                while((st1=br.readLine())!=null)
                    st2+=st1+"\n";
                    System.out.println(st2);
                np.ta.setText(st2);
                cntdone=1;
                br.close();
            }catch(Exception e){}
        
        if(cntdone==1)
        {
        np.setTitle("MY NotePad");
        np.setSize(800,700);
        np.setVisible(true);
        this.setVisible(false);
        }
    }
    
    void blankTemplate()
    {
        np.setTitle("MY NotePad");
        np.setSize(800,700);
        np.setVisible(true);
        this.setVisible(false);
    }
}
