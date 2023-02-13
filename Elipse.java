import java.awt.*;
import java.util.*;

public class Elipse extends Figura
{
    protected Ponto p1, p2,p3;
    protected int raio1, raio2;
    
    
    public Elipse (int x1, int y1, int x2, int y2, int x3, int y3)
    {
        this (x1, y1, x2, y2, x3, y3, Color.BLACK, Color.WHITE);
        double raio1Double = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double raio2Double = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        this.raio1 = (int) raio1Double;
        this.raio2 = (int) raio2Double;
    }
	
    public Elipse (int x1, int y1, int x2, int y2, int x3, int y3, Color cor, Color cor2)
    {
        super(cor, cor2);
        

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
        double raio1Double = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double raio2Double = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        this.raio1 = (int) raio1Double;
        this.raio2 = (int) raio2Double;
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        int   x3 =  Integer.parseInt(quebrador.nextToken());
        int   y3 =  Integer.parseInt(quebrador.nextToken());


        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
                            

        this.p1  = new Ponto (x1,y1,cor);
        this.p2  = new Ponto (x2,y2,cor);
        this.p3  = new Ponto (x3,y3,cor);
        this.cor = cor;
        this.cor2 = cor2;
        double raio1Double = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double raio2Double = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        this.raio1 = (int) raio1Double;
        this.raio2 = (int) raio2Double;
       

    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor());
    }
    public void setP3 (int x, int y)
    {
        this.p3 = new Ponto (x,y,this.getCor());
    }
    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }
    public Ponto getP3 ()
    {
        return this.p3;
    }
    public int getRaio1 ()
    {
        return this.raio1;
    }
    public int getRaio2 ()
    {
        return this.raio2;
    }


    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawOval(this.p1.getX(), this.p1.getY(),   // ponto inicial 
                    this.raio1, this.raio2);  // tamanho
        g.setColor(this.cor2);
        g.fillOval(this.p1.getX(), this.p1.getY(),   // ponto inicial 
                    this.raio1, this.raio2);
        g.setColor(this.cor);
        
                    
    }

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
               this.p3.getX() +
               ":" +
               this.p3.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Elipse))
            return false;

        Elipse elip = (Elipse)obj;

        if (this.raio1!=elip.raio1)
            return false;

        if (this.raio2!=elip.raio2)
            return false;

        if (this.p1.equals(elip.p1))
            return false;

        if (this.p2.equals(elip.p2))
            return false;

        if (this.p3.equals(elip.p3))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.raio1).hashCode();
        ret = 7*ret + new Integer(this.raio2).hashCode();
        ret = 7*ret + this.p1.hashCode();
        ret = 7*ret + this.p2.hashCode();
        ret = 7*ret + this.p3.hashCode();

        return ret;
    }


    public Elipse (Elipse modelo) throws Exception
    {
        this.raio1 = modelo.raio1; 
        this.raio2 = modelo.raio2; 
        this.p1   = modelo.p1;   
        this.p2  = modelo.p2;  
        this.p3  = modelo.p3; 
    }

    public Object clone ()
    {
        Elipse ret=null;

        try
        {
            ret = new Elipse (this);
        }
        catch (Exception erro)
        {} 

        return ret;
    }
}