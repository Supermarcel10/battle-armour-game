package game.objects.pickup;

import city.cs.engine.*;
import game.objects.abstractBody.Body;
import game.objects.tank.Player;
import org.jbox2d.common.Vec2;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static game.MainGame.pickups;
import static game.objects.tank.Tank.halfSize;


public class Pickup extends Body implements SensorListener {
	private static final Shape shape = new CircleShape((halfSize / 1.5f) * scaleFactor);
	public final PickupType type;

	public Pickup(PickupType type, Vec2 position) {
		super(0f);
		this.type = type;

		setPosition(position);

		// Add to pickups list.
		pickups.add(this);

		// Create a new ghostly fixture
		new GhostlyFixture(this, shape);
		Sensor sensor = new Sensor(this, shape);

		this.setFillColor(java.awt.Color.RED);

		setBullet(true);

		// Add collision listener.
		sensor.addSensorListener(this);

		// Add a timer to destroy the pickup after 10 seconds.
		new Timer(10000, e -> {
			destroyPickup();

			// Stop after first execution to allow for GC.
			((Timer) e.getSource()).stop();
		}).start();
	}

	private void destroyPickup() {
		destroy();
		pickups.remove(this);
	}

	public Pickup(PickupType type, int x, int y) {
		this(type, new Vec2(x, y));
	}

	// TODO: Fix this sometimes not registering
	@Override
	public void beginContact(@NotNull SensorEvent e) {
		if (e.getContactBody() instanceof Player p) {
			p.pickUp(this);
			destroyPickup();
		}
	}

	@Override public void endContact(SensorEvent sensorEvent) {}
}