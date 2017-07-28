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
        	System.out.println("enemy = " + enemy);
        	
            ahead(radius);
            turnLeft(angle);
            turnGunLeft(angle);
            fireBullet(getEnergy());
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
    	if (enemy.name == null || enemy.name.equals(e.getName())) {
    		enemy.update(e, getX(), getY(), getHeading());
    	}
    	
        fire(1);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnLeft(90 - e.getBearing());
    }

	public class Enemy {
		String name;
		double bearing;
		double distance;
		double energy;
		double heading;
		double velocity;
		private double x;
		private double y;

		public void update(ScannedRobotEvent e, double myX, double myY, double myHeading) {
			this.bearing = e.getBearing();
			this.distance = e.getDistance();
			this.energy = e.getEnergy();
			this.heading = e.getHeading();
			this.velocity = e.getVelocity();
			this.name = e.getName();
			

			double absBearingDeg = (myHeading + e.getBearing());
			if (absBearingDeg < 0) {
				absBearingDeg += 360;
			}

			this.x = myX + Math.sin(Math.toRadians(absBearingDeg)) * e.getDistance();
			this.y = myY + Math.cos(Math.toRadians(absBearingDeg)) * e.getDistance();
		}

		@Override
		public String toString() {
			return "Enemy [name=" + name + ", bearing=" + bearing + ", distance=" + distance + ", energy=" + energy
					+ ", heading=" + heading + ", velocity=" + velocity + ", x=" + x + ", y=" + y + "]";
		}
	}
}
