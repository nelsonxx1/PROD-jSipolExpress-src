package modelo.borrame;

import modelo.util.bean.BeanVO;

/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Value Object used to store an image.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * @author Mauro Carniel
 * @version 1.0
 */
public class ImageVO extends BeanVO {

    private String imageName;
    private byte[] image;

    public ImageVO() {
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
