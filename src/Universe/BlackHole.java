package Universe;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import states.RandomStringGenerator;

public class BlackHole extends Entity
{
	private String name = (String) RandomStringGenerator.generateRandomString(8);

	public BlackHole(Handler handler, float x, float y, long mass)
	{
		super(handler, x, y, mass);
		this.density = 500;
		calculateRadius(mass);
	}
	
	public BlackHole(Handler handler, float x, float y, long mass, Vector v)
	{
		super(handler, x, y, mass);
		this.density = 500;
		calculateRadius(mass);
		this.velocity = v;
	}
	
	@Override
	public void tick()
	{
		calculateAttraction();
	}

	@Override
	public void render(Graphics g)
	{
		int zoomDiameter = (int) (radius/handler.getCamera().getZoomLevel()*2);
		g.setColor(Color.WHITE);
		g.drawString(name,(int)(drawX+10), (int)(drawY));
		if(zoomDiameter >= 2)
		{
			g.setColor(Color.BLACK);
			g.fillOval((int)(drawX), (int)(drawY), zoomDiameter, zoomDiameter);
			g.setColor(Color.WHITE);
			g.drawOval((int)(drawX-1), (int)(drawY-1), (int)zoomDiameter+2, (int)zoomDiameter+2);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillOval((int)(drawX), (int)(drawY), 2, 2);
			g.setColor(Color.WHITE);
			g.drawOval((int)(drawX-1), (int)(drawY-1), (int)2, (int)2);
		}
		
		if(handler.getApplication().debugMode)
		{
			drawDebugVectors(g);
			g.setColor(Color.WHITE);
			g.drawString(mass+"",(int)(drawX), (int)(drawY));
		}
	}
	
	public void drawDebugVectors(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawLine((int)drawXCenter, (int)drawYCenter, (int)drawXCenter + (int)(this.velocity.getX()*20), (int)drawYCenter + (int)(this.velocity.getY()*20));
		g.setColor(Color.GREEN);
		g.drawLine((int)drawXCenter, (int)drawYCenter, (int)drawXCenter + (int)(this.netForce.getX()),(int)drawYCenter + (int)(this.netForce.getY()));
	}
}