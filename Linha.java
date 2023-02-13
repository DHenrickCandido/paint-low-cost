import java.awt.*;
import java.util.*;

public class Linha extends Figura
{
    protected Ponto p1, p2;
    
    public Linha (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK);
    }
	
    public Linha (int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor, cor);

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
    }

    public Linha (String s)
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

        this.p1  = new Ponto (x1,y1,cor);
        this.p2  = new Ponto (x2,y2,cor);
        this.cor = cor;
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

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                   this.p2.getX(), this.p2.getY());  // ponto final
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

        if (!(obj instanceof Linha))
            return false;

        Linha lin = (Linha)obj;

        if (this.p1.equals(lin.p1))
            return false;

        if (this.p2.equals(lin.p2))
            return false;



        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.p1.hashCode();
        ret = 7*ret + this.p2.hashCode();
 

        return ret;
    }


    public Linha (Linha modelo) throws Exception
    {
        this.p1   = modelo.p1;   
        this.p2  = modelo.p2;  

    }

    public Object clone ()
    {
        Linha ret=null;

        try
        {
            ret = new Linha (this);
        }
        catch (Exception erro)
        {} 

        return ret;
    }
}