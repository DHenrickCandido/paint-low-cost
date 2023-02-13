import java.awt.*;
import java.util.*;

public class Circulo extends Figura
{
    protected Ponto p1, p2;
    protected int diametro;
    
    
    public Circulo (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK, Color.WHITE);
        double diametroDouble = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        this.diametro = (int) diametroDouble;
    }
	
    public Circulo (int x1, int y1, int x2, int y2, Color cor, Color cor2)
    {
        super(cor, cor2);
        

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
        double diametroDouble = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        this.diametro = (int) diametroDouble;
    }

    public Circulo (String s)
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
        double diametroDouble = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        this.diametro = (int) diametroDouble;
       

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

    public int getDiametro()
    {
        return this.diametro;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawOval(this.p1.getX(), this.p1.getY(),   // ponto inicial 
                    this.diametro, this.diametro);  // tamanho
        g.setColor(this.cor2);
        g.fillOval(this.p1.getX(), this.p1.getY(),   // Preencher com a cor desejada
                    this.diametro, this.diametro);
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

        if (!(obj instanceof Circulo))
            return false;

        Circulo circ = (Circulo)obj;

        if (this.diametro!=circ.diametro)
            return false;

        if (this.p1.equals(circ.p1))
            return false;

        if (this.p2.equals(circ.p2))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.diametro).hashCode();
        ret = 7*ret + this.p1.hashCode();
        ret = 7*ret + this.p2.hashCode();

        return ret;
    }


    public Circulo (Circulo modelo) throws Exception
    {
        this.diametro = modelo.diametro; 
        this.p1   = modelo.p1;   
        this.p2  = modelo.p2;  
    }

    public Object clone ()
    {
        Circulo ret=null;

        try
        {
            ret = new Circulo (this);
        }
        catch (Exception erro)
        {} 

        return ret;
    }
}