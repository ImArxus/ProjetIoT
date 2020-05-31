package main;

import javafx.scene.image.ImageView;

public class EquipementImageView {

	private ImageView imageView;

	public EquipementImageView() {
		setImageView(new ImageView());
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

}
