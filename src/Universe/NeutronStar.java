package Universe;

import main.Handler;
import states.RandomStringGenerator;

import java.awt.*;

public class NeutronStar extends Entity{

    String name = (String) RandomStringGenerator.generateRandomString(8);

    public NeutronStar(Handler handler, float x, float y, long mass) {
        super(handler, x, y, mass);
        this.density = 70;
        calculateRadius(mass);
    }

    public NeutronStar(Handler handler, float x, float y, long mass, Vector v)
    {
        super(handler, x, y, mass);
        this.density = 70;
        calculateRadius(mass);
        this.velocity = v;
    }

    @Override
    public void tick() {
        calculateAttraction();
    }

    @Override
    public void render(Graphics g) {

        int zoomDiameter = (int) (radius/handler.getCamera().getZoomLevel())*2;

        g.setColor(Color.CYAN);
        g.drawString(name,(int)(drawX+10), (int)(drawY));

        if(zoomDiameter >= 2)
            g.fillOval((int)(drawX), (int)(drawY), zoomDiameter, zoomDiameter);
        else
            g.fillOval((int)(drawX), (int)(drawY), 1, 1);

        if(handler.getApplication().debugMode)
        {
            drawDebugVectors(g);
            g.setColor(Color.WHITE);
            g.drawString(velocity.getMagnitude()+"",(int)(drawX), (int)(drawY-10));
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
