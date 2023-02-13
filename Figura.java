import java.awt.*;

public abstract class Figura
{
    protected Color cor;
    protected Color cor2;

    protected Figura ()
    {
        this (Color.BLACK, Color.WHITE);
        
    }
	  
    protected Figura (Color cor, Color cor2)
    {
        this.cor = cor;
        this.cor2 = cor2;
    }
	  
    public void setCor (Color cor)
    {
        this.cor = cor;
    }

    public void setCor2 (Color cor)
    {
        this.cor2 = cor;
    }
	  
    public Color getCor()
    {
    	return this.cor;
    }
    public Color getCor2()
    {
    	return this.cor2;
    }



    public abstract boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Figura))
            return false;

        Figura fig = (Figura)obj;


        if (this.cor.equals(fig.cor))
            return false;

        if (this.cor2.equals(fig.cor2))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.cor.hashCode();
        ret = 7*ret + this.cor2.hashCode();

        return ret;
    }

    public Object clone ()
    {
        Figura ret=null;

        try
        {
            ret = new Figura (this);
        }
        catch (Exception erro)
        {} 

        return ret;
    }

    public String toString ()
    {
        String ret="";

        ret+="Cor: "+this.cor+"\n";
        ret+="Cor2: "+this.cor2;

        return ret;
    }

    public abstract void    torneSeVisivel (Graphics g);
}