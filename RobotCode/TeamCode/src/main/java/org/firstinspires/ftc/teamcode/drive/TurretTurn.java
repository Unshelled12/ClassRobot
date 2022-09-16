package org.firstinspires.ftc.teamcode.drive;

public class TurretTurn {
    public TurretTurn()
    {
        populateArray();
    }
    class Junction
    {
        public int X;
        public int Y;
    }

    class MinDist
    {
        public double distance;
        public int index;
        public double x;
        public double y;
    }

    public static Junction junctionArray[] = new Junction[25];

    //populate junctionArray
    public static void populateArray()
    {
        int index = 0;
        int yCoord = -48;
        int xCoord;
        for (int i = 0; i < 5; i++)
        {
            xCoord = -48;
            for (int j = 0; j < 5; j++)
            {
                junctionArray[index].X = xCoord;
                junctionArray[index].Y = yCoord;

                xCoord += 24;
            }

            yCoord += 24;
        }
    }

    public static double findDegree(double robotX, double robotY, double robotHeading)
    {
        MinDist min = null;
        min.distance = 100;
        double a;
        double b;
        double distance;
        double degree;
        double heading;

        //Find distance from each junction
        for (int i = 0; i < 25; i++)
        {
            //Find sides of triangle
            a = robotX - junctionArray[i].X;
            b = robotY - junctionArray[i].Y;

            //Pythagorean theorem
            distance = Math.sqrt(a*a + b*b);

            if (distance < min.distance)
            {
                min.distance = distance;
                min.index = i;
                min.x = a;
                min.y = b;
            }
        }

        degree = Math.asin(min.x / min.distance);

        if (min.x > 0 && min.y > 0)
        {
            heading = 180 + degree;
        }
        else if (min.x > 0 && min.y < 0)
        {
            heading = 360 - degree;
        }
        else if (min.x < 0 && min.y > 0)
        {
            heading = 180 - degree;
        }
        else
        {
            heading = degree;
        }

        return heading;
    }
}