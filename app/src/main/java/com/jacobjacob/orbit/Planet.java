package com.jacobjacob.orbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Planet {
    public ArrayList<Trail> Trails = new ArrayList<>();
    public float x, y, z, vel, angle, mass, vx, vy, vz, time, ax, ay, az, n, a, xscreen, yscreen, density, trail, radius, vx1, vy1,vz1, angle1, ax1, ay1, az1, drawx, drawy, drawz, wl;
    int red, green, blue;
    public boolean trails, reverse, randomColor;
    public ArrayList<Float> TrailX = new ArrayList<>();
    public ArrayList<Float> TrailY = new ArrayList<>();
    public ArrayList<Float> TrailZ = new ArrayList<>();
    String name;

    Planet(float x1, float y1, float z1, float vel1, float angle1, float mass1, String name1, float density1, int red1, int green1, int blue1) {

        name = name1;
        mass = mass1;
        density = density1;
        radius = 20;
        //radius = mass;
        x = x1;
        y = y1;
        z = z1;
        vel = vel1;
        angle = angle1;
        red = red1;
        green = green1;
        blue = blue1;
        randomColor = true;
        Random rr = new Random();
        z = rr.nextInt(100) - 50;
        z = z1;
        if (name1 == "Random") {
            int min = 0;
            Random r = new Random();

            x = r.nextInt((int) x - min) + min;
            y = r.nextInt((int) y - min) + min;
            //z = r.nextInt((int) z - min) + min;

            int max = (int) vel;
            vel = r.nextInt(max - min) + min;

            min = 0;
            max = (int) angle1;
            angle = r.nextInt(max - min) + min;

            min = 0;
            max = (int) mass1;
            mass = r.nextInt(max - min) + min;

            min = 0;
            max = 255;
            red = r.nextInt(max - min) + min;
            green = r.nextInt(max - min) + min;
            blue = r.nextInt(max - min) + min;
        }
        vx = vel * (float) Math.cos(Math.toRadians(angle));
        vy = vel * (float) Math.sin(Math.toRadians(angle));
        vz = vel * (float) Math.cos(Math.toRadians(angle));
        //vz = 0;

        vx1 = vx;
        vy1 = vy;
        vz1 = vz;

        trail = 50;
        trails = true;
        reverse = true;
    }

    void update(float dt) {

        vx += ax * dt;
        vy += ay * dt;
        vz += az * dt;

        x += vx * dt;
        y += vy * dt;
        z += vz * dt;

        ax1 = ax;
        ay1 = ay;
        az1 = az;

        vx1 = vx;
        vy1 = vy;
        vz1 = vz;

        ax = 0;
        ay = 0;
        az = 0;

        drawx = x;
        drawy = y;
        drawz = z;

        trails = !trails;

        if (trail != 0) { //trails & trails2 & trails3 & //name != "Sun" &

            if (n < trail) {
                TrailX.add(x);
                TrailY.add(y);
                TrailZ.add(z);
            }
            n += 1;
        }
        if (trail == n & reverse) { //trail + 1
            Collections.reverse(TrailX);
            Collections.reverse(TrailY);
            Collections.reverse(TrailZ);
            reverse = false;
        }

        if (trail < n) {
            for (int i = TrailX.size(); i >= 0; ) {
                if (i == 0) {
                    TrailX.set(0, x);
                    TrailY.set(0, y);
                    TrailZ.set(0, z);
                }
                if (i > 0 & i < TrailX.size() & i < TrailY.size()& i < TrailZ.size()) { //

                    TrailX.set(i, TrailX.get(i - 1));
                    TrailY.set(i, TrailY.get(i - 1));
                    TrailZ.set(i, TrailZ.get(i - 1));
                }
                i--;
            }
            n = trail + 1;
        }
    }
}

