/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 *
 * @author liteh
 */
public class CustomCursor {
    // https://www.flaticon.com/free-icon/refresh_1330172?term=rotate&page=1&position=4
    private static final String RotateCursorPngBase64 = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAQAAADZc7J/AAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAHdElNRQfkBQkIIQh4vv+nAAACHElEQVRIx5WVz09TQRDHP31UDFC1B4GYRhNMTKpc5GTqwQAajZFgAmmI/4BcjFe9qMUbcMNi4p2EEDhgJPGkmEiMxuqhBzlBPNhHjMRI/dG0tC6H7tvuNq993e+e3ux8vzM7szsvhB8i3GCY8/QRpYTLd7Ks8YYCLaCXNH8QPusvaWLNyYd5yG9fsrcKTNPeiN7DhuFcxiVDhhxlw/6ek9ziCwMm/RxflUuJBcbpUnvHSLKkyfykjOCxGb1GX+GMb4ZneW5ksqaffUPFnmxapbsUlcBOzfxI0a8F9OgmJS2HmNc4r/KTAfQoBeMQo1VzWp09CG3MsasJTAFE5LUpNShdPcIM84RvCATLABNSbaEluocQCWYYBHgmBcasBDR8kLeuy5J3lftcoRN+IBC41oGzCARZh6MA5KwF/gEQdwgBULYWqL7J/w6bAHRYC5wAYC9MggQDfLSkH6EHgC3rwBJJ2fynDjDENBdxrAS8W/MKYAWBIEeay4RbosfZRyDI0wkwpT2PXeZoCxRYld7z1c/RurEZDaDfUZ6nqoaYRi96b7whrsv0Bama0VUC+9yTV8sft9VEequP9xd1w7K/QelWlc82x/WtFIIiO2q7wjITWi0iJFlUqQu2iJva/bxjhF7WjUwquGT4hEvFsK+b0XWEeUA+4NeW4lDzKnczy54vOc+817ga/CveziVGuMBposAvtvnMa17KGWDgAEIoPUMhK/yaAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDIwLTA1LTA5VDA4OjMzOjA4KzAwOjAw9FSlngAAACV0RVh0ZGF0ZTptb2RpZnkAMjAyMC0wNS0wOVQwODozMzowOCswMDowMIUJHSIAAAAZdEVYdFNvZnR3YXJlAHd3dy5pbmtzY2FwZS5vcmeb7jwaAAAAAElFTkSuQmCC";
    // https://www.flaticon.com/free-icon/full-screen_570953?term=resize&page=1&position=1
    private static final String ResizePngBase64 = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAQAAADZc7J/AAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAHdElNRQfkBQkJAiNraRuxAAAAsElEQVRIx+2VTRJAMAyFH3cobsi4Ji6gZhynFv4aLU0mFhaeXebla0YyCTDAkS8l6u6yICVLAqLEGVXybV8G416xg4OFg4URpNstawOsPG4Vp/sAQIDwnR6Ai6AuAuAgro6g7c8IVo33JvZfihtFfVq73JBYI5sUgzqI1YJB+/UJlWiDmKCNJSbNIBWwmlGOp7MR5jb9AaFeKC+tNOVSVa919WFRnDb1cc3Ri16+qlsAhe6x6WqlYEUAAAAldEVYdGRhdGU6Y3JlYXRlADIwMjAtMDUtMDlUMDk6MDI6MzUrMDA6MDCwuhcHAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDIwLTA1LTA5VDA5OjAyOjM1KzAwOjAwweevuwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAAASUVORK5CYII=";
    
    private CustomCursor() {
        // Prevents creating instance
    }
    private static Cursor createCursor(String base64) throws IOException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        byte pngBytes[] = Base64.getDecoder().decode(base64);
        ByteArrayInputStream str = new ByteArrayInputStream(pngBytes);
        BufferedImage image = ImageIO.read(str);
        return toolkit.createCustomCursor(image, new Point(0, 0), "회전");
    }
    public static Cursor getRotateCursor() throws IOException {
        return createCursor(RotateCursorPngBase64);
    }
    public static Cursor getResizeCursor() throws IOException {
        return createCursor(ResizePngBase64);
    }
}
