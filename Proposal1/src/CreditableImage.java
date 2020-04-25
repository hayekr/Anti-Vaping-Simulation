import javafx.scene.image.Image;

/**
 * This class enables pairing images with their source/credit
 */
public class CreditableImage {
	private Image image;
	private String credit;

	/**
	 * Constructor for CreditableImage
	 * @param image
	 * @param credit
	 */
	CreditableImage(Image image, String credit) {
		this.image = image;
		this.credit = credit;
	}

	/**
	 * Used to get the image
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Used to get the credit for an image
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}
}
