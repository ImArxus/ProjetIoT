package main;

import javafx.scene.image.ImageView;

public class EquipementImageView {
	
	private static ImageView imageView = new ImageView();

	public static ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		EquipementImageView.imageView = imageView;
	}

}
