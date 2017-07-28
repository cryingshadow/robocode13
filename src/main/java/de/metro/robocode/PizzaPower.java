package de.metro.robocode;

import java.awt.Color;

import robocode.*;

public class PizzaPower extends AdvancedRobot {

    @Override
    public void run() {
		setColors(Color.cyan, Color.magenta, Color.cyan);

        double radius = 100.0;
        double angle = 90.0;

        while (true) {
            ahead(radius);
            turnLeft(angle);
            turnGunLeft(angle);
            fireBullet(getEnergy());
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnLeft(90 - e.getBearing());
    }

    
}
