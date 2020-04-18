
package StreetFighter;

/**
 *
 * @author RummanBUET
 */
public class XYComponent {

    double dx;
    double dy;

    public XYComponent(double x, double y) {
        dx = x;
        dy = y;
    }
    
    public void Reset(){
        dx = 0 ;
        dy = 0 ;
    }
}
