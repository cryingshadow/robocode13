package de.metro.robocode;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;

public class PizzaPower extends AdvancedRobot {
	Enemy enemy = new Enemy();
	
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

	public class Enemy {
		double bearing;
		double distance;
		double energy;
		double heading;
		double velocity;
		String name;
		private double x;
		private double y;

		@Override
		public String toString() {
			return "Enemy [bearing=" + bearing + ", distance=" + distance + ", energy=" + energy + ", heading="
					+ heading + ", velocity=" + velocity + ", name=" + name + ", x=" + x + ", y=" + y + "]";
		}
	}
}
