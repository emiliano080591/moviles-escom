/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author DAE
 */
public class Piramide extends MIDlet implements CommandListener {

    private Display d;
    private Command c;
    private Piramide.Perspectiva k;

    public Piramide() {
        d = Display.getDisplay(this);
        k = new Piramide.Perspectiva();
    }

    class Perspectiva extends Canvas {

        int centerX, centerY, maxX, maxY, minMaxXY;
        Piramide.Obj obj = new Piramide.Obj();

        public Perspectiva() {
            centerX = maxX / 2;
            centerY = maxY / 2;
        }

        int iX(float x) {
            return (int) (centerX + x);
        }

        int iY(float y) {
            return (int) (centerY - y);
        }

        void line(Graphics g, int i, int j) {
            Piramide.Point2D p = obj.vScr[i], q = obj.vScr[j];
            g.drawLine(iX(p.x), iY(p.y), iX(q.x), iY(q.y));
        }

        public void paint(Graphics g) {
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(0, 0, 0);
            
            maxX = getWidth();
            maxY = getHeight();
            
            minMaxXY = Math.min(maxX, maxY);
            
            centerX = maxX / 2;
            centerY = maxY / 2;
            
            obj.d = obj.rho * minMaxXY / obj.objSize;
            obj.eyeAndScreen();
            
            line(g, 0, 1);
            line(g, 1, 2);
            line(g, 2, 3);
            line(g, 3, 0); // inferior  horizontal  edge
            line(g, 1, 4);
            line(g, 2, 4);
            line(g, 3, 4); // superior horizontal edge
            line(g, 0, 4);
            //line(g, 0, 3);
            //line(g, 1, 4);
            //line(g, 2, 5);
          //  line(g, 3, 7); // verticla edges
        }

        protected void keyPressed(int keyCode) {
            int arriba = getKeyCode(UP);
            int abajo = getKeyCode(DOWN);
            int izq = getKeyCode(LEFT);
            int dcha = getKeyCode(RIGHT);
            if (keyCode == arriba) {
                obj.phi += (float) 0.1;
                obj.rho = (obj.phi / obj.theta) * getHeight();
                repaint();
            } else if (keyCode == abajo) {
                obj.phi -= (float) 0.1;
                obj.rho = (obj.phi / obj.theta) * getHeight();
                repaint();
            } else if (keyCode == izq) {
                obj.theta += (float) 0.1;
                obj.rho = (obj.phi / obj.theta) * getHeight();
                repaint();
            } else if (keyCode == dcha) {
                obj.theta -= (float) 0.1;
                obj.rho = (obj.phi / obj.theta) * getHeight();
                repaint();
            }
        }
    }

    class Obj {	// Data from the 3D objects 

        float rho, theta = 0.3F, phi = 1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; // elements of matrix V 
        Piramide.Point3D[] w;	// universal coordinates
        Piramide.Point2D[] vScr; // screen coordinates

        Obj() {	//change the x,y,z with 0,1 to create prisma, cone, cylinder, pyramid and sphere.
            w = new Piramide.Point3D[5];
            vScr = new Piramide.Point2D[5];

            w[0] = new Piramide.Point3D(1, -1, -1); // desde la base
	w[1] = new Piramide.Point3D(1, 1, -1);
	w[2] = new Piramide.Point3D(-1, 1, -1);
	w[3] = new Piramide.Point3D(-1, -1, -1);
        
	w[4] = new Piramide.Point3D(0, 0, 1); 
	//w[5] = new Point3D(2, 2, 2); 
	//w[5] = new Point3D(0, 2, 2); 
	//w[6] = new Point3D(-1, -1, 1);

            objSize = (float) Math.sqrt(60F); // = sqrt(2*2 + 2*2 + 2*2) the distance between two opposite vectors
            rho = 10 * objSize;		//to change the perspective
        }

        void initPersp() {
            float costh = (float) Math.cos(theta), sinth = (float) Math.sin(theta), cosph = (float) Math.cos(phi), sinph = (float) Math.sin(phi);
            v11 = -sinth;
            v12 = -cosph * costh;
            v13 = sinph * costh;
            v21 = costh;
            v22 = -cosph * sinth;
            v23 = sinph * sinth;
            v32 = sinph;
            v33 = cosph;
            v43 = -rho;
        }

        void eyeAndScreen() {
            initPersp();
            for (int i = 0; i < 5; i++) {
                Piramide.Point3D p = w[i];
                float x = v11 * p.x + v21 * p.y, y = v12 * p.x + v22 * p.y + v32 * p.z, z = v13 * p.x + v23 * p.y + v33 * p.z + v43;
                vScr[i] = new Piramide.Point2D(-d * x / z, -d * y / z);
            }
        }
    }

    class Point2D {

        float x, y;

        Point2D(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    class Point3D {

        float x, y, z;

        Point3D(double x, double y, double z) {
            this.x = (float) x;
            this.y = (float) y;
            this.z = (float) z;
        }
    }

    public void startApp() {
        d.setCurrent(k);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
