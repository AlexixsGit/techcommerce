package co.edu.cedesistemas.leveling.functional;

import co.edu.cedesistemas.leveling.generics.Sorter;
import co.edu.cedesistemas.leveling.model.geometry.Circle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // Ordenar numeros ...
        List<Integer> numbers = Arrays.asList(5, 8, 2, 10, 4, 6, 1);
        // Solucion 1: Usando clase anonima
        /*
        SortFunction<Integer> sorter1 = new SortFunction<>() {
            @Override
            public void sort(List<Integer> list) {
                Collections.sort(list);
            }
        };
        sorter1.sort(numbers);
        System.out.println(numbers);

         */

        // Solucion 2: Usando expresión lambda:
        SortFunction<Integer> sorter2 = l -> Sorter.bubbleSort(numbers);
        sorter2.sort(numbers);
        System.out.println(numbers);

        // EJERCICIO:
        Circle circle = new Circle(30);
        // ************ CAMBIAR ESTAS LINEAS POR EXPRESION LAMBDA **********************
        ShapeMultiplier<Circle, Double> shapeMultiplier = new ShapeMultiplierImpl<>();
        Circle newCircle = shapeMultiplier.multiply(circle, 200D);
        double area = newCircle.area();
        System.out.println("new area: " + area);
        // *****************************************************************************

        // INSERTE EXPRESION LAMBDA ACA PARA OBTENER EL MISMO RESULTADO
        // ....

        ShapeMultiplier<Circle, Double> shapeMultiplier2 = (c, v) -> c.scale(v);
        Circle circle1 = shapeMultiplier2.multiply(circle, 200D);
        System.out.println("new area: " + circle1.area());


    }
}