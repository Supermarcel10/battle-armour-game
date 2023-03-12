package game.objects;

import city.cs.engine.*;
import game.main.Game;
import org.jbox2d.common.Vec2;

import static game.main.Game.scaleFactor;
import static game.main.Game.scaledGridSize;


public class Block extends StaticBody {
	private Vec2 offset = new Vec2(0, 0);
	private final Shape shape;
	private final BodyImage image;
	protected StaticBody body;
	protected int maxHealth = 1;
	protected int health = maxHealth;

	public Block(Shape shape, BodyImage image) {
		super(Game.world);
		this.shape = shape;
		this.image = image;
	}

	public Block(Shape shape, BodyImage image, Vec2 offset) {
		super(Game.world);
		this.shape = shape;
		this.image = image;
		this.offset = offset;
	}

	public void createBody(World world, int x, int y) {
		body = new StaticBody(world, shape);
		body.addImage(image).setOffset(offset);
		body.setPosition(new Vec2(((scaledGridSize * 2) * x) * scaleFactor, ((scaledGridSize * 2) * y) * scaleFactor));
	}

	public void createBody(World world) {
		createBody(world, 0, 0);
	}

	public void createBody(World world, Vec2 position) {
		body = new StaticBody(world, shape);
		body.addImage(image).setOffset(offset);
		body.setPosition(position);
	}

	public void damage(int damage) {
		for (int i = 0; i < damage; i++) {
			damage();
		}
	}

	public void damage() {
		health--;

		if (health <= 0) {
			body.destroy();
		}
		// TODO: Add damage animation.
	}
}