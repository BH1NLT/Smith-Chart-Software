//Smith2.java
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//**********************************************************
 class Smith2 extends Panel
{
	protected Point origin;
	protected double radius;
	protected boolean mousemodeflag=false;
	protected Image offscreen;   
	protected Graphics offg;
	protected int size;
	protected java.text.DecimalFormat myformat=new java.text.DecimalFormat("#0.0000");
	private boolean begin=true;
	protected double ph;
	protected double gama;
	protected Button rotate=new Button("Rotate ");
	protected Label  intro=new Label("�糤��/lamda");
	protected Panel below;
	//�糤��
		public TextField dian_length=new TextField("0",10);
	protected Point mouseLoc;
	protected boolean rot=false;
	//******************************************************
	protected ControlPanel controlPanel;  //�������
	//protected ControlPanel controlPanel2;  //�������
	public int getWidth()
	{		
		return size+controlPanel.getWidth()+35;
	}
	public int getHeight()
	{
		return size;
	}
	public Smith2()
	{
		size=400;		
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
		rotate.addActionListener(new B1());
		setLayout(new BorderLayout() );
		controlPanel=new ControlPanel();
		setSize(590,600);
		below=new Panel();
		below.setLayout(new FlowLayout());
		below.add(intro);
		below.add(dian_length);
		below.add(rotate);
		add(controlPanel,"East");
		add(below,"South");
		start();
	}
	public void addNotify()
	{
		super.addNotify();
		offscreen=createImage(size,size+200);
		offg=offscreen.getGraphics();
	}
	protected int changetoX(double x)
	{
		return (int)(x*200+200);
	}
	protected int changetoY(double y)
	{
		return (int) (200-200*y);
	}
	protected double changetox(int X)
	{
		return (X-200)/200.0;
	}
	protected double changetoy(int Y)
	{
		return (200-Y)/200.0;
	}
	protected int sitatoX(double sita)
	{
		return (int)(200*sita/Math.PI);
	}
	protected double Xtosita(int X)
	{
		return X*Math.PI/200;
	}
	protected double sitatoy(double gama,double sita)
	{
		return Math.sqrt(1+gama*gama+2*gama*Math.cos(sita));
	}
	protected double sitatoysin(double gama,double sita)
	{
		return Math.sqrt(1+gama*gama-2*gama*Math.cos(sita));
	}
	protected int ytoY(double y)
	{
		return (int)(-50*y+550);
	}
	public void paint(Graphics g)
	{
		offg.setColor(Color.orange);
		offg.fillRect(0,0,700,400);
		offg.setColor(Color.GRAY);
		offg.fillOval(0,0,400,400);
		//draw lamda circle
		offg.setColor(Color.white);
		offg.drawOval(0,0,400,400);
		offg.drawOval(50,50,300,300);
		offg.drawOval(100,100,200,200);
		offg.drawOval(150,150,100,100);
		//draw r cirlce
		offg.setColor(Color.BLUE);
		offg.drawOval(changetoX(1.0/3),changetoY(1.0/3),(int)(200*2/3.0),(int)(200*2/3.0));
		offg.drawOval(changetoX(-0.6),changetoY(0.8),(int)(200*1.6),(int)(200*1.6));
		offg.drawOval(changetoX(0),changetoY(0.5),200,200);
		//draw x circle
		offg.setColor(Color.orange);
		//......
		offg.drawOval(changetoX(0.75),changetoY(0.5),(int)(200*0.5),(int)(200*0.5));
		offg.drawOval(changetoX(0.5),changetoY(1.0),(int)(200),(int)(200));
		offg.drawOval(changetoX(0.0),changetoY(2.0),(int)(200*2),(int)(200*2));
		offg.drawOval(changetoX(-1.0),changetoY(4.0),(int)(200*4),(int)(200*4));
		offg.drawOval(changetoX(0.75),changetoY(0.0),(int)(200*0.5),(int)(200*0.5));
		offg.drawOval(changetoX(0.5),changetoY(0.0),(int)(200),(int)(200));
		offg.drawOval(changetoX(0.0),changetoY(0.0),(int)(200*2),(int)(200*2));
		offg.drawOval(changetoX(-1.0),changetoY(0.0),(int)(200*4),(int)(200*4));
		//need to be done later
		
		offg.drawLine(0,200,400,200);
		offg.drawLine(200,0,200,400);
		//mousemodeflag=controlPanel.mousemode.getState();
		if ((mousemodeflag&&
				(mouseLoc.x-200)*(mouseLoc.x-200)+(mouseLoc.y-200)*(mouseLoc.y-200)<=40000)||rot)
		{
			if (rot==true)
				rot=false;
			begin=false;
			double x=changetox(mouseLoc.x);//gama u
			double y=changetoy(mouseLoc.y);//gama v
			 gama=Math.sqrt(x*x+y*y);//٤�����ֵ
			double row=(1+gama)/(1-gama);
			double k=1/row;
			int R=(int) (200*Math.sqrt(x*x+y*y));//٤�����ֵ�������Ŵ��
			double r=(1-x*x-y*y)/((1-x)*(1-x)+y*y); //normalized resistor��һ������
			double X=2*y/((1-x)*(1-x)+y*y);//��һ���翹
			double x0=(r-1)/(r+1),y0=1/(1+r),w=2/(1+r),h=2/(1+r);//resistor circle
			
			controlPanel.realtext.setText(myformat.format(x));
			controlPanel.imaginetext.setText(myformat.format(y));
			controlPanel.rowtext.setText(myformat.format(row));
			controlPanel.absolute.setText(myformat.format(gama));
			if (mouseLoc.x>=200&&mouseLoc.y>=200)
			{	
				ph=360+180*Math.atan(y/x)/Math.PI;
				controlPanel.phase.setText(myformat.format(ph));
			}
			else if (mouseLoc.x<200&&mouseLoc.y<=200)
				{
				ph=180+180*Math.atan(y/x)/Math.PI;
				controlPanel.phase.setText(myformat.format(ph));
				}
			else if (mouseLoc.x<200&&mouseLoc.y>=200)
				
				{
				ph=180+180*Math.atan(y/x)/Math.PI;
				controlPanel.phase.setText(myformat.format(ph));
				}
			else
				{
				ph=180*Math.atan(y/x)/Math.PI;
				controlPanel.phase.setText(myformat.format(ph));}
			controlPanel.Ktext.setText(myformat.format(k));
			controlPanel.normalr.setText(myformat.format(r));
			controlPanel.normalx.setText(myformat.format(X));
			offg.setColor(Color.red);
			offg.drawLine(mouseLoc.x,mouseLoc.y,200,200); //gama line
			offg.drawOval(changetoX(x0),changetoY(y0),
					(int)(200*w),(int)(200*h));
			offg.drawOval(200-R,200-R,
					2*R,2*R);
			if (mouseLoc.y<=200)
			{
				offg.drawOval(changetoX(1-1/X),changetoY(2/X),
						(int)(200*2/Math.abs(X)),(int)(200*2/Math.abs(X)));
			}
			else
			{
				offg.drawOval(changetoX(1-1/Math.abs(X)),changetoY(0.0),
						(int)(200*2/Math.abs(X)),(int)(200*2/Math.abs(X)));
			}
		double amp=1+gama;
		
			offg.setColor(Color.green);
			offg.fillRect(0,400,600,200);
			//draw cos
			offg.setColor(Color.black);
			offg.drawLine(0,497,400,497);
			offg.drawLine(0,447,400,447);
			offg.drawLine(0,547,400,547);

			offg.drawString("Voltage-----", 0, 420);
			offg.setColor(Color.red);
			offg.drawString("Current-----", 0, 440);
			for (int i=0;i<400;i++)
			{
				offg.setColor(Color.red);
				offg.drawString(".",i,ytoY(sitatoy(gama,Xtosita(i)*2)));//����
				offg.setColor(Color.BLACK);
				offg.drawString(".",i,ytoY(sitatoysin(gama,Xtosita(i)*2)));//��ѹ
			}
			int xposi;
			if (ph>=0&&ph<180)
				 xposi=(int)(5/9.0*ph+300);
			else
				 xposi=(int)(5/9.0*ph+100);
			offg.setColor(Color.BLACK);
			offg.drawLine(xposi,447,xposi,547);
			offg.setColor(Color.black);
			offg.drawOval(mouseLoc.x-2,mouseLoc.y-2,3,3);
			g.drawImage(offscreen,0,0,this);
		}
		if (begin)
		{	
			offg.setColor(Color.green);
			offg.fillRect(0,400,400,200);
			//draw cos
			offg.setColor(Color.black);
			offg.drawLine(0,497,400,497);
			offg.drawLine(0,447,400,447);
			offg.drawLine(0,547,400,547);

			offg.drawString("Voltage-----", 0, 420);
			offg.setColor(Color.red);
			offg.drawString("Current-----", 0, 440);
			g.drawImage(offscreen,0,0,this);
		}

	}
	public void update(Graphics g)
	{//����
		paint(g);
	}			
	//**************************************************************************
	class ML extends MouseAdapter
	{   
		public void mousePressed(MouseEvent evt)
		{
			mousemodeflag=!mousemodeflag;
		}	
		public void mouseExited(MouseEvent evt)
		{
			repaint();
		}
	}
	private class MML extends MouseMotionAdapter
    {
		public void mouseMoved(MouseEvent evt)
		{
			int x=evt.getX();
			int y=evt.getY();
			mouseLoc=new Point(x,y);
			repaint();		
		}
	}
    class B1 implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    //		if ((mouseLoc.x-200)*(mouseLoc.x-200)+(mouseLoc.y-200)*(mouseLoc.y-200)>40000||begin)
    	//	return;
    		if (begin)
    			return;
    		//if ((mouseLoc.x-200)*(mouseLoc.x-200)+(mouseLoc.y-200)*(mouseLoc.y-200)>40000)
    			//return;
    		if (mousemodeflag)
    			return;
    		double lamdalength=Double.valueOf(dian_length.getText()).doubleValue();
    		double nph=((ph-lamdalength*720)%360+360)%360;
    		double x=gama*Math.cos(2*Math.PI*nph/360);
    		double y=gama*Math.sin(2*Math.PI*nph/360);	
    		mouseLoc.x=changetoX(x);
    		mouseLoc.y=changetoY(y);
    		rot=true;
    		repaint();
    	}
    }
		//*******************************************
	public void start()
	{
		repaint();
	}
//*****************���������************************************
	
class ControlPanel extends Panel
{	
	protected Label  row=new Label("פ��ϵ��");
	//����ϵ���ı�ǩ
	protected Label  real=new Label("����ϵ��ʵ��");
	protected Label  imagine=new Label("����ϵ���鲿");
	protected Label  abslb=new Label("����ϵ��ģֵ");
	protected Label  phalb=new Label("����ϵ����λ");
	//��һ���迹�ı�ǩ
	protected Label  rlb=new Label("��һ������");
	protected Label  xlb=new Label("��һ���翹");
	protected Label  Klb=new Label("K");
	
	//protected Label  intro2=new Label("ǳ�߱�ʾ��ѹ");
	//protected Label  intro3=new Label("���߱�ʾ�糤��");
	
	public Checkbox mousemode=new Checkbox("using mouse");
	//����ϵ��
	public TextField realtext=new TextField("0",10);
	public TextField imaginetext=new TextField("0",10);
	public TextField absolute=new TextField("0",10);
	public TextField phase=new TextField("0",10);
	//��һ���迹
	public TextField normalr=new TextField("0",10);
	public TextField normalx=new TextField("0",10);
	//פ��ϵ��
	public TextField rowtext=new TextField("0",10);
	public TextField Ktext=new TextField("0",10);
	
//*****************************************************
	public int getWidth()
	{
		return 45;
	}
	public int getHeight()
	{
		return size;
	}
//***************************************
	public ControlPanel()
	{		
		setSize(100,60);		
		setLayout(new GridLayout(12,1,0,10));
    	
		add(abslb);
		add(absolute);
		add(phalb);
		add(phase);
		add(real);	
		add(realtext);
		add(imagine);
		add(imaginetext);
		add(rlb);
		add(normalr);
		add(xlb);
		add(normalx);
		add(row);
		add(rowtext);
		add(Klb);
		add(Ktext);

		setBackground(new Color(120,120,200));								
	}	
	}
}