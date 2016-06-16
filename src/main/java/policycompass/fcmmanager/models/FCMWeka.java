package policycompass.fcmmanager.models;

/**
 * Created by Mohammed Bahja  on 2016-05-16.
 */
public class FCMWeka {
    private float minimum;
    private float maximum;
    private float mean;
    private float stdDev;
    private String wekaString;


    public float getMinimum() { return minimum; }
    public void setMinimum(float minimum) { this.minimum = minimum; }
    public float getMaximum() { return maximum; }
    public void setMaximum(float maximum) { this.maximum = maximum; }
    public float getMean() { return mean; }
    public void setMean(float mean) { this.mean = mean; }
    public float getStdDev() { return stdDev; }
    public void setStdDev(float stdDev) { this.stdDev = stdDev; }
    public String getWekaString() { return wekaString; }
    public void setWekaString(String wekaString) { this.wekaString = wekaString; }
}