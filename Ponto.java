import java.awt.*;
import java.util.*;

public class Ponto extends Figura
{
    protected int x,  y;

    public Ponto (int x, int y)
    {
        this (x, y, Color.BLACK);
    }
	  
    public Ponto (int x, int y, Color cor)
    {
        super (cor, cor);

        this.x = x;
        this.y = y;
    }

    public Ponto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        this.x = Integer.parseInt(quebrador.nextToken());
        this.y = Integer.parseInt(quebrador.nextToken());

        this.cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                              Integer.parseInt(quebrador.nextToken()),  // G
                              Integer.parseInt(quebrador.nextToken())); // B
    }

    public void setX (int x)
    {
        this.x = x;
    }
	  
    public void setY (int y)
    {
        this.y = y;
    }
	  
    public int getX ()
    {
        return this.x;
    }
	  
    public int getY ()
    {
    	return this.y;
    }
	  
    public void torneSeVisivel (Graphics g)
    {
    	g.setColor (this.cor);
    	g.drawLine (this.x,this.y,this.x,this.y);
    }

    public String toString()
    {
        return "p:" +
               this.x +
               ":" +
               this.y +
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

        if (!(obj instanceof Ponto))
            return false;

        Ponto pont = (Ponto)obj;

        if (this.y!=pont.y)
            return false;

        if (this.x!=pont.x)
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.y).hashCode();
        ret = 7*ret + new Integer(this.x).hashCode();

        return ret;
    }


    public Ponto (Ponto modelo) throws Exception
    {
        this.y = modelo.y; 
        this.x = modelo.x; 

    }

    public Object clone ()
    {
        Ponto ret=null;

        try
        {
            ret = new Ponto (this);
        }
        catch (Exception erro)
        {} 

        return ret;
    }
}