//Smith.java
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import sunw.util.*;

public class Smith1 extends Applet
{
	Smith2 table;
	
	public void init()
	{//��ʼ�����
		table=new Smith2();
		//setBackground(Color.red);
		setLayout(new BorderLayout());
		setSize(table.getSize());
		add(table,"Center");
	}
	public int getWidth()//�õ�����ֵ
	{
		return table.getWidth();
	}
	
	public int getHeight()//�õ�����ֵ
	{
		return table.getHeight();
	}
	

	public static void main(String[] args)
	{
		try{
		Frame f=new Frame("Smith");	//
		f.setResizable(false);		
		f.setLayout(new BorderLayout());
		
		Smith1 my=new Smith1();  		
		my.init();
		my.start();
			
		f.setSize(my.getWidth(),20+my.getHeight());//
		f.add(my,"Center");
		
		f.setVisible(true);
		}
		catch(Exception e)
		{
		}
	}
	
}
