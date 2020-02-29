/*
 * Copyright 2019 Andy Turner, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.v2d.geometry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.ojalgo.function.constant.BigMath;
import uk.ac.leeds.ccg.math.Math_BigDecimal;
import uk.ac.leeds.ccg.v2d.core.V2D_Environment;

/**
 * 2D points.
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class V2D_Point extends V2D_Geometry implements V2D_FiniteGeometry,
        Comparable {

    /**
     * The x coordinate.
     */
    public BigDecimal x;

    /**
     * The y coordinate.
     */
    public BigDecimal y;

    /**
     * Creates a default Vector_Point2D with: x = null; y = null;
     *
     * @param e
     */
    public V2D_Point(V2D_Environment e) {
        super(e);
    }

    /**
     * @param p Vector_Point2D
     */
    public V2D_Point(V2D_Point p) {
        super(p.e);
        x = p.x;
        y = p.y;
    }

    /**
     * @param p Vector_Point2D
     * @param toRoundToX BigDecimal toRound {@code p.x} to.
     * @param toRoundToY BigDecimal toRound {@code p.y} to.
     */
    public V2D_Point(V2D_Point p, BigDecimal toRoundToX,
            BigDecimal toRoundToY) {
        super(p.e);
        x = e.round(p.x, toRoundToX);
        y = e.round(p.y, toRoundToY);
    }

    /**
     * this.x = new BigDecimal(x.toString()); this.y = new
     * BigDecimal(y.toString());
     * setDecimalPlacePrecision(Math.max(x.scale(),y.scale()));
     *
     * @param x BigDecimal
     * @param y BigDecimal
     * @param ve
     */
    public V2D_Point(V2D_Environment ve, BigDecimal x, BigDecimal y) {
        super(ve);
        this.x = new BigDecimal(x.toString());
        this.y = new BigDecimal(y.toString());
    }

    /**
     * @param e The vector environment.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param dp The decimal places.
     */
    public V2D_Point(V2D_Environment e, BigDecimal x, BigDecimal y,
            int dp) {
        super(e);
        this.x = new BigDecimal(x.toString());
        this.y = new BigDecimal(y.toString());
    }

    /**
     * @param e The vector environment.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param toRoundToX BigDecimal toRoundToX_BigDecimal
     * @param toRoundToY BigDecimal
     */
    public V2D_Point(V2D_Environment e, BigDecimal x, BigDecimal y,
            BigDecimal toRoundToX, BigDecimal toRoundToY) {
        super(e);
        this.x = e.round(x, toRoundToX);
        this.y = e.round(y, toRoundToY);
    }

    /**
     * @param e The vector environment.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public V2D_Point(V2D_Environment e, String x, String y) {
        super(e);
        this.x = new BigDecimal(x);
        this.y = new BigDecimal(y);
    }

    /**
     * @param e The vector environment.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param dp The decimal places.
     */
    public V2D_Point(V2D_Environment e, String x, String y, int dp) {
        super(e);
        this.x = new BigDecimal(x);
        this.y = new BigDecimal(y);
    }

    /**
     * @param e The vector environment.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public V2D_Point(V2D_Environment e, double x, double y) {
        super(e);
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
    }

    /**
     * @param e The vector environment.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param toRoundToX For rounding x.
     * @param toRoundToY For rounding y.
     */
    public V2D_Point(V2D_Environment e, double x, double y,
            BigDecimal toRoundToX, BigDecimal toRoundToY) {
        super(e);
        this.x = e.round(BigDecimal.valueOf(x), toRoundToX);
        this.y = e.round(BigDecimal.valueOf(y), toRoundToY);
    }

    /**
     * For rounding.
     *
     * @param r The number to round to.
     */
    public void roundTo(BigDecimal r) {
        x = e.round(x, r);
        y = e.round(y, r);
    }

    @Override
    public String toString() {
        return "Point2D(x=" + x.toString() + " y="
                + y.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof V2D_Point) {
            if (hashCode() == ((V2D_Point) o).hashCode()) {
                if (compareTo(o) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.x != null ? this.x.hashCode() : 0);
        hash = 67 * hash + (this.y != null ? this.y.hashCode() : 0);
        return hash;
    }

    /**
     * @param o Object to compare to.
     * @return 0 if this is the same as o and +1 or -1 otherwise.
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof V2D_Point) {
            V2D_Point p = (V2D_Point) o;
            int cty = y.compareTo(p.y);
            if (cty != 0) {
                return cty;
            } else {
                return x.compareTo(p.x);
            }
        }
        return 1;
    }

    /**
     * 
     * @param l
     * @param t tolerance
     * @return 
     */
    public boolean getIntersects(V2D_LineSegment l, BigDecimal t) {
        return l.getIntersects(this, t);
    }

    /**
     * Get the distance to/from this to {@code p} precise to {@code dp2} number
     * of decimal places.
     *
     * @param p A point.
     * @param dp2 Decimal place precision.
     * @return The distance from {@code p} to this.
     */
    public BigDecimal getDistance(V2D_Point p, int dp2) {
        if (this.equals(p)) {
            return BigDecimal.ZERO;
        }
        BigDecimal diffx = this.x.subtract(p.x);
        BigDecimal diffy = this.y.subtract(p.y);
//        return BigMath.POW.invoke(diffx.pow(2).add(diffy.pow(2)),
//                BigMath.HALF);
        return Math_BigDecimal.sqrt(diffx.pow(2).add(diffy.pow(2)), dp2,
                e.bd.getRoundingMode());
    }

    /**
     * Get the angle from {@code p} to the Y-Axis clockwise.
     *
     * @param p A point.
     * @return Angle to the y axis clockwise.
     */
    public double getAngle_double(V2D_Point p) {
        double dx = p.x.doubleValue() - x.doubleValue();
        double dy = p.y.doubleValue() - y.doubleValue();
        if (dy == 0.0d) {
            if (dx == 0.0d) {
                return 0.0d;
            } else {
                if (dx > 0.0d) {
                    return Math.PI / 2.0d;
                } else {
                    return (3.0d * Math.PI) / 2.0d;
                }
            }
        } else {
            if (dy > 0.0d) {
                if (dx == 0.0d) {
                    return 0.0d;
                } else {
                    if (dx > 0.0d) {
                        return Math.atan(dx / dy);
                    } else {
                        return (2.0d * Math.PI) - Math.atan(Math.abs(dx) / dy);
                    }
                }
            } else {
                // dy < 0.0d
                if (dx == 0.0d) {
                    return Math.PI;
                } else {
                    if (dx > 0.0d) {
                        return Math.PI - Math.atan(dx / Math.abs(dy));
                    } else {
                        return Math.PI + Math.atan(Math.abs(dx) / Math.abs(dy));
                    }
                }
            }
        }
    }

    /**
     * Get the angle from {@code p} to the Y-Axis clockwise. Uses BigMath
     * default precision.
     *
     * @param p A point.
     * @return Angle to the Y-Axis clockwise. Default 0.0d.
     */
    public BigDecimal getAngle_BigDecimal(V2D_Point p) {
        BigDecimal dx = p.x.subtract(x);
        BigDecimal dy = p.y.subtract(y);
        if (dy.compareTo(BigDecimal.ZERO) == 0) {
            if (dx.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            } else {
                if (dx.compareTo(BigDecimal.ZERO) == 1) {
                    return BigMath.HALF_PI;
                } else {
                    return new BigDecimal("3").multiply(BigMath.HALF_PI);
                }
            }
        } else {
            if (dy.compareTo(BigDecimal.ZERO) == 1) {
                if (dx.compareTo(BigDecimal.ZERO) == 0) {
                    return BigDecimal.ZERO;
                } else {
                    if (dx.compareTo(BigDecimal.ZERO) == 1) {
                        return BigMath.ATAN.invoke(BigMath.DIVIDE.invoke(dx, dy));
                    } else {
                        return BigMath.SUBTRACT.invoke(BigMath.TWO_PI,
                                BigMath.ATAN.invoke(BigMath.DIVIDE.invoke(
                                        BigMath.ABS.invoke(dx), dy)));
                        //return (2.0d * Math.PI) - Math.atan(Math.abs(dx) / dy);
                    }
                }
            } else {
                // dy < 0.0d
                if (dx.compareTo(BigDecimal.ZERO) == 0) {
                    return BigMath.PI;
                } else {
                    if (dx.compareTo(BigDecimal.ZERO) == 1) {
                        return BigMath.SUBTRACT.invoke(
                                BigMath.PI,
                                BigMath.ATAN.invoke(BigMath.DIVIDE.invoke(dx, BigMath.ABS.invoke(dy))));
                        //return Math.PI - Math.atan(dx / Math.abs(dy));
                    } else {
                        return BigMath.ADD.invoke(
                                BigMath.PI,
                                BigMath.ATAN.invoke(BigMath.DIVIDE.invoke(BigMath.ABS.invoke(dx), BigMath.ABS.invoke(dy))));
                        //return Math.PI + Math.atan(Math.abs(dx) / Math.abs(dy));
                    }
                }
            }
        }
    }

    public BigDecimal getGradient(V2D_Point p, int scale, RoundingMode rm) {
        BigDecimal xDiff0 = x.subtract(p.x);
        BigDecimal yDiff0 = y.subtract(p.y);
        if (yDiff0.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        } else {
            if (xDiff0.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            }
            return xDiff0.divide(yDiff0, scale, rm);
        }
    }

    @Override
    public V2D_Envelope getEnvelope2D() {
        return new V2D_Envelope(e, x, y);
    }

    /**
     * double[] result = new double[2]; result[0] = this.x.doubleValue();
     * result[1] = this.y.doubleValue(); return result;
     *
     * @return double[}
     */
    public double[] to_doubleArray() {
        double[] result = new double[2];
        result[0] = x.doubleValue();
        result[1] = y.doubleValue();
        return result;
    }
}
