import java.awt.*;
import java.util.*;

public class Quadrado extends Figura
{
    protected Ponto p1, p2;
    protected int lado;
    
    
    public Quadrado (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK, Color.WHITE);
        double ladoDouble = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        this.lado = (int) ladoDouble;
    }
	
    public Quadrado (int x1, int y1, int x2, int y2, Color cor, Color cor2)
    {
        super(cor, cor2);
        

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
        double ladoDouble = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        this.lado = (int) ladoDouble;
    }

    public Quadrado (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
                            

        this.p1  = new Ponto (x1,y1,cor);
        this.p2  = new Ponto (x2,y2,cor);
        this.cor = cor;
        this.cor2 = cor2;
        double ladoDouble = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        this.lado = (int) ladoDouble;
       

    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor());
    }

    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

   // public void torneSeVisivel (Graphics g)
   // {
         //  Graphics2D g2 = (Graphics2D) g;
            

      //  g.setColor(this.cor);
      //  g2.draw(new Rectangle2D.Double(this.p1.getX(),this.p1.getY(),
      //                         lado,
     //                          lado));
      //  g.setColor(this.cor2);
      //  g.fillOval(this.p1.getX(), this.p1.getY(),   // ponto inicial 
     //               this.diametro, this.diametro);
      //  g.setColor(this.cor);
    //
                    
   // }

    public String toString()
    {
        return "r:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}