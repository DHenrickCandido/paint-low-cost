import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto     = new JButton ("Ponto"),
                      btnLinha     = new JButton ("Linha"),
                      btnCirculo   = new JButton ("Circulo"),
                      btnElipse    = new JButton ("Elipse"),
                      btnTexto     = new JButton ("Texto"),
                      btnCorFora   = new JButton ("Fora"),
                      btnCorDentro = new JButton ("Dentro"),
                      btnAbrir     = new JButton ("Abrir"),
                      btnSalvar    = new JButton ("Salvar");
                      
    protected MeuJPanel pnlDesenho = new MeuJPanel ();
    
    protected JLabel statusBar1 = new JLabel ("Mensagem:"),
                     statusBar2 = new JLabel ("Coordenada:");

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaRaio, esperaLocalCirculo, 
                      esperaLocalElipse, esperaPrimeiroRaio, esperaSegundoRaio;

    protected Color corFora = Color.BLACK;
    protected Color corDentro = Color.WHITE;
    protected Ponto p1;
    protected Ponto p2;
    protected String ferramentaEmUso;
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Gr�fico");

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnTextoImg = ImageIO.read(getClass().getResource("resources/text.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo text.png n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCorForaImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorFora.setIcon(new ImageIcon(btnCorForaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCorDentroImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorDentro.setIcon(new ImageIcon(btnCorDentroImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener (new DesenhoDePonto ());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo ());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnCorFora.addActionListener (new SelecionarCorFora());
        btnCorDentro.addActionListener (new SelecionarCorDentro());

        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnTexto);
        pnlBotoes.add (btnCorFora);
        pnlBotoes.add (btnCorDentro);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (1000,600);
        this.setVisible (true);
    }

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {
	public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e)
        {
          
            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corFora));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
            }
            else
                if (esperaInicioReta)
                {
                    p1 = new Ponto (e.getX(), e.getY(), corFora);
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    statusBar1.setText("Mensagem: clique o ponto final da figura");    
                 }
                 else
                    if (esperaFimReta)
                    {
                        esperaInicioReta = false;
                        esperaFimReta = false;
                        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corFora));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        statusBar1.setText("Mensagem:");    
                    }
                    else
                    if (esperaLocalCirculo)
                    {
                        p1 = new Ponto (e.getX(), e.getY(), corFora);
                        esperaLocalCirculo = false;
                        esperaRaio = true;
                        statusBar1.setText("Mensagem: clique o ponto final da figura");    
                     }
                     else
                    if (esperaRaio)
                    {
                        esperaLocalCirculo = false;
                        esperaRaio = false;
                        figuras.add (new Circulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corFora, corDentro));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        statusBar1.setText("Mensagem:");    
                    }
                    else
                    if (esperaLocalElipse)
                    {
                        p1 = new Ponto (e.getX(), e.getY(), corFora);
                        esperaLocalElipse = false;
                        esperaPrimeiroRaio = true;
                        statusBar1.setText("Mensagem: clique o ponto do primeiro raio da elipse");    
                     }
                     
                     else
                    if (esperaPrimeiroRaio)
                    {
                        p2 = new Ponto (e.getX(), e.getY(), corFora);
                        esperaLocalElipse = false;
                        esperaPrimeiroRaio = false;
                        esperaSegundoRaio = true;
                        statusBar1.setText("Mensagem: clique o ponto do segundo  raio da elipse");
                        
                    }else
                    if (esperaSegundoRaio)
                    {
                      
                        esperaLocalElipse = false;
                        esperaPrimeiroRaio = false;
                        esperaSegundoRaio = false;
                        figuras.add (new Elipse(p1.getX(), p1.getY(), p2.getX(), p2.getY(), e.getX(), e.getY(), corFora, corDentro));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        statusBar1.setText("Mensagem:");    
                    }
        }
        
        public void mouseReleased (MouseEvent e)
        {}
        
        public void mouseClicked (MouseEvent e)
        {}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {}

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
            esperaPonto      = true;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaLocalCirculo = false;
            esperaRaio      = false;
            esperaLocalElipse = false;
            esperaPrimeiroRaio = false;
            esperaSegundoRaio = false;

              ferramentaEmUso = "ponto";

              statusBar1.setText("Mensagem: clique no local do ponto desejado");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = true;
            esperaFimReta    = false;
            esperaLocalCirculo = false;
            esperaRaio      = false;
            esperaLocalElipse = false;
            esperaPrimeiroRaio = false;
            esperaSegundoRaio = false;

            ferramentaEmUso = "reta";

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaLocalCirculo = true;
            esperaRaio      = false;
            esperaLocalElipse = false;
            esperaPrimeiroRaio = false;
            esperaSegundoRaio = false;

            ferramentaEmUso = "circulo";

            statusBar1.setText("Mensagem: clique o local do circulo");
        }
    }

        protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaLocalCirculo = false;
            esperaRaio      = false;
            esperaLocalElipse = true;
            esperaPrimeiroRaio = false;
            esperaSegundoRaio = false;


            ferramentaEmUso = "Elipse";

            statusBar1.setText("Mensagem: clique o local da elipse");
        }
    }

   // protected class Salvar implements ActionListener
   // {
      //  public void actionPerformed (ActionEvent e)    
      //  {
      //      JFileChooser fileChooser = new JFileChooser();
      //      if (fileChooser.showSaveDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
      //        File file = fileChooser.getSelectedFile();
              // save to file

       //     statusBar1.setText("Mensagem: clique o local do circulo");
       //     }
       // }
  //  }
    protected class SelecionarCorFora extends JFrame implements ActionListener {    
        JButton b;    
        Container c;    
        SelecionarCorFora(){    
            c=getContentPane();    
            c.setLayout(new FlowLayout());         
            b=new JButton("color");    
            b.addActionListener(this);         
            c.add(b);    
        }    
        public void actionPerformed(ActionEvent e) {    
        Color initialcolor=Color.RED;    
        Color color=JColorChooser.showDialog(this,"Selecione a cor desejada",initialcolor);    
        c.setBackground(color); 
        corFora = color;   
        }    
        
        }    
        protected class SelecionarCorDentro extends JFrame implements ActionListener {    
            JButton b;    
            Container c;    
            SelecionarCorDentro(){    
                c=getContentPane();    
                c.setLayout(new FlowLayout());         
                b=new JButton("color");    
                b.addActionListener(this);         
                c.add(b);    
            }    
            public void actionPerformed(ActionEvent e) {    
            Color initialcolor=Color.RED;    
            Color color=JColorChooser.showDialog(this,"Selecione a cor desejada",initialcolor);    
            c.setBackground(color); 
            corDentro = color;   
            }    
            
            }    

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}
