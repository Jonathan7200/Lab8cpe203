import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

public class FileRead {

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        try {
            String filename = args[0];
            Scanner input = new Scanner(new File(filename));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] words = line.split(",");
                points.add(new Point(words[0], words[1], words[2]));
            }
            points = points.stream()
                    .filter(p->!(p.getZ() > 2.0))
                    .map(p -> new Point((p.getX()*0.5)-150,(p.getY()*0.5)-37,p.getZ()*0.5))
                    .collect(Collectors.toList());

            Formatter formatted = new Formatter("drawMe.txt");
            for (Point point : points) {
                formatted.format("%s, %s, %s%n", point.getX(), point.getY(), point.getZ());
            }
            formatted.close();
        }
        catch (Exception e){System.err.println(e.getMessage()); System.err.println("No File available"); System.exit(1);}
    }
}