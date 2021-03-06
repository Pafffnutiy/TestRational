import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

public class Tests {
    static final int MIN = -10000000, MAX = 10000000;  //For selection a random number

    @Test //№1
    public void testStandardConstructor() {
        Rational standard = new Rational();
        assertEquals("Standard constructor returns wrong numerator", 0, standard.getNumerator());
        assertEquals("Standard constructor returns wrong denominator", 1, standard.getDenominator());
    }

    @Test //№2
    public void testParamConstructorWithPosDenominator() {
        for (int i = 0; i < 10; ++i) {
            int numer = getRandomNumber(MIN, MAX);
            int denom = getRandomNumber(1, MAX);
            int gcd = getGCD(numer, denom);
            Rational obj = new Rational(numer, denom);

            assertEquals("Constructor with params returns wrong numerator", numer / gcd, obj.getNumerator());
            assertEquals("Constructor with params returns wrong denominator", denom / gcd, obj.getDenominator());
        }
    }

    @Test //№3
    public void testParamConstructorWithNegDenominator() {
        for (int i = 0; i < 10; ++i) {
            int numer = getRandomNumber(MIN, MAX);
            int denom = getRandomNumber(MIN, -1);
            int gcd = getGCD(numer, denom);
            Rational obj = new Rational(numer, denom);

            assertEquals("Constructor with params returns wrong numerator", -numer / gcd, obj.getNumerator());
            assertEquals("Constructor with params returns wrong denominator", Math.abs(denom) / gcd, obj.getDenominator());
        }
    }

    @Test(expected = ArithmeticException.class) //№4
    public void testParamConstructorWithZeroDenominator() {
        int denom = 0;
        int numer = getRandomNumber(MIN, MAX);
        Rational obj = new Rational(numer, denom);
    }

    @Test //№5
    public void testNumeratorSetter() {
        for (int i = 0; i < 10; ++i) {
            int numer = getRandomNumber(MIN, MAX);
            int denom = getRandomNumberNoZero(MIN, MAX);
            Rational obj = new Rational(numer, denom);

            int oldDenom = obj.getDenominator();

            int newNumerator = getRandomNumber(MIN, MAX);
            obj.setNumerator(newNumerator);
            int gcd = getGCD(newNumerator, oldDenom);

            assertEquals("Numerator setter returns wrong numerator", newNumerator / gcd, obj.getNumerator());
            assertEquals("Numerator setter returns wrong denominator", oldDenom / gcd, obj.getDenominator());
        }
    }

    @Test //№6
    public void testDenominatorSetter() {
        for (int i = 0; i < 10; ++i) {
            int numer = getRandomNumber(MIN, MAX);
            int denom = getRandomNumberNoZero(MIN, MAX);
            Rational obj = new Rational(numer, denom);

            int oldNumerator = obj.getNumerator();

            int newDenominator = getRandomNumberNoZero(MIN, MAX);
            obj.setDenominator(newDenominator);
            int gcd = getGCD(oldNumerator, newDenominator);

            if (newDenominator < 0) oldNumerator *= -1;

            assertEquals("Denominator setter returns wrong numerator", oldNumerator / gcd, obj.getNumerator());
            assertEquals("Denominator setter returns wrong denominator", Math.abs(newDenominator) / gcd, obj.getDenominator());
        }
    }

    @Test(expected = ArithmeticException.class) //№7
    public void testDenominatorSetterZero(){
        int numer = getRandomNumber(MIN, MAX);
        int denom = getRandomNumberNoZero(MIN, MAX);
        Rational obj = new Rational(numer, denom);

        obj.setDenominator(0);
    }

    @Test //№8
    public void testToString() {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(5, -4);
        Rational obj3 = new Rational(2, 3);

        String str1 = "0/1";
        String str2 = "-5/4";
        String str3 = "2/3";

        assertEquals("Func ToString works incorrect", str1, obj1.toString());
        assertEquals("Func ToString works incorrect", str2, obj2.toString());
        assertEquals("Func ToString works incorrect", str3, obj3.toString());
    }

    @Test //№9
    public void testEquals() {
        Rational obj1 = new Rational(2313, -3213);
        Rational obj2 = new Rational(-2313, 3213);
        Rational obj3 = new Rational(506, 695);
        Rational obj4 = new Rational(-2313, 940);
        Rational obj5 = new Rational(452, 695);

        String str1 = String.join("/", String.valueOf(2313), String.valueOf(-3213));

        assertTrue("Func Equals works incorrect", obj1.equals(obj2));
        assertTrue("Func Equals works incorrect", obj2.equals(obj1));
        assertFalse("Func Equals works incorrect", obj1.equals(obj3));
        assertFalse("Func Equals works incorrect", obj3.equals(obj1));
        assertFalse("Func Equals works incorrect", obj4.equals(obj2));
        assertFalse("Func Equals works incorrect", obj3.equals(obj5));
        assertFalse("Func Equals works incorrect", obj1.equals(2313));
        assertFalse("Func Equals works incorrect", obj1.equals(str1));
    }

    @Test //№10
    public void testLess() {
        Rational obj1 = new Rational(123, 256);
        Rational obj2 = new Rational(123, 512);

        assertTrue("Func less works incorrect for equal numerators and different denominators", obj2.less(obj1));

        obj1 = new Rational(123, -256);
        obj2 = new Rational(123, 512);

        assertTrue("Func less works incorrect for different numerators and denominators ", obj1.less(obj2));

        obj1 = new Rational(-123, -256);
        obj2 = new Rational(-321, -256);

        assertTrue("Func less works incorrect for different numerators and equal denominators", obj1.less(obj2));

        obj1 = new Rational(-123, 824);
        obj2 = new Rational(-321, 946);

        assertTrue("Func less works incorrect for different numerators and denominators", obj2.less(obj1));

        obj1 = new Rational(-123, 256);
        obj2 = new Rational(-123, 256);

        assertFalse("Func less works incorrect for equal rationals", obj1.less(obj2));
    }

    @Test //№11
    public void testLessOrEqual() {
        Rational obj1 = new Rational(123, 256);
        Rational obj2 = new Rational(123, 512);

        assertTrue("Func lessOrEqual works incorrect for equal numerators and different denominators", obj2.lessOrEqual(obj1));

        obj1 = new Rational(123, -256);
        obj2 = new Rational(123, 512);

        assertFalse("Func lessOrEqual works incorrect for different numerators and denoiminators (because of incorrect work of Func less)", obj2.lessOrEqual(obj1));

        obj1 = new Rational(-123, 256);
        obj2 = new Rational(-123, 256);

        assertTrue("Func lessOrEqual works incorrect for equal rationals", obj1.lessOrEqual(obj2));
    }

    @Test //№12
    public void testPlus() {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(654, 9070);
        Rational obj3 = new Rational(4562, 34);
        Rational obj4 = new Rational(4, 3);
        Rational obj5 = new Rational(12, -9);

        assertEquals("Func Plus works incorrect", new Rational(31358062, 231285), obj2.plus(obj3.plus(obj4)));
        assertEquals("Func Plus works incorrect", obj3, obj1.plus(obj3));
        assertEquals("Func Plus works incorrect", obj1, obj4.plus(obj5));
    }

    @Test //№13
    public void testMultiply() {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(654, 900);
        Rational obj3 = new Rational(456, 34);
        Rational obj4 = new Rational(3, 4);
        Rational obj5 = new Rational(12, -9);

        assertEquals("Func Multiply works incorrect", new Rational(-16568, 1275), obj2.multiply(obj3.multiply(obj5)));
        assertEquals("Func Multiply works incorrect", new Rational(-1, 1), obj4.multiply(obj5));
        assertEquals("Func Multiply works incorrect", obj1, obj4.multiply(obj1));
    }

    @Test //№14
    public void testMinus() {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(654, 900);
        Rational obj3 = new Rational(456, 34);
        Rational obj4 = new Rational(3, 4);
        Rational obj5 = new Rational(9, 12);

        assertEquals("Func Minus works incorrect", new Rational(-60869, 5100), obj2.minus(obj3.minus(obj4)));
        assertEquals("Func Minus works incorrect", obj1, obj4.minus(obj5));
        assertEquals("Func Minus works incorrect", new Rational(-654, 900), obj1.minus(obj2));
    }

    @Test(expected = ArithmeticException.class) //№15
    public void testDivideByZeroRational() {
        Rational obj1 = new Rational();
        Rational obj2 = new Rational(getRandomNumber(MIN, MAX), getRandomNumberNoZero(MIN, MAX));

        obj2.divide(obj1);
    }

    @Test //#16
    public void testDivide() {
        Rational obj1 = new Rational(-654, 900);
        Rational obj2 = new Rational(-456, 34);
        Rational obj3 = new Rational(3, 4);
        Rational obj4 = new Rational(17, 4);

        assertEquals("Func Divide works incorrect", new Rational(-76, 1), obj2.divide(obj3.divide(obj4)));
        assertEquals("Func Divide works incorrect", new Rational(1853, 34200), obj1.divide(obj2));
    }

    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private static int getRandomNumberNoZero(int min, int max) {
        int res = (int) (Math.random() * ((max - min) + 1)) + min;
        if (res == 0) res += getRandomNumber(1, max);

        return res;
    }

    private int getGCD(int a, int b) {
        int n = abs(a);
        int d = abs(b);

        while (n > 0 && d > 0) {
            if (n > d) {
                n %= d;
            } else {
                d %= n;
            }
        }

        return n + d;
    }
}