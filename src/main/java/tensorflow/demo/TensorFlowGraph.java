package tensorflow.demo;

import org.tensorflow.*;

public class TensorFlowGraph {

    public static Graph createGraph(Double[] theta) {
        Graph graph = new Graph();

        // define constants thetas
        Operation theta_1 = graph.opBuilder("Const", "theta_1").setAttr("dtype", DataType.fromClass(Double.class))
                .setAttr("value", Tensor.<Double>create(theta[0], Double.class)).build();
        Operation theta_2 = graph.opBuilder("Const", "theta_2").setAttr("dtype", DataType.fromClass(Double.class))
                .setAttr("value", Tensor.<Double>create(theta[1], Double.class)).build();
        Operation theta_3 = graph.opBuilder("Const", "theta_3").setAttr("dtype", DataType.fromClass(Double.class))
                .setAttr("value", Tensor.<Double>create(theta[2], Double.class)).build();


        // define placeholders x_2, x_3
        Operation x_2 = graph.opBuilder("Placeholder", "x_2").setAttr("dtype", DataType.fromClass(Double.class)).build();
        Operation x_3 = graph.opBuilder("Placeholder", "x_3").setAttr("dtype", DataType.fromClass(Double.class)).build();

        // defining the function
        Operation theta_2_x_2 = graph.opBuilder("Mul", "theta_2_x_2").addInput(theta_2.output(0)).addInput(x_2.output(0)).build();
        Operation theta_3_x_3 = graph.opBuilder("Mul", "theta_3_x_3").addInput(theta_3.output(0)).addInput(x_3.output(0)).build();
        Operation theta1Andtheta2x2 = graph.opBuilder("Add", "theta1Andtheta2x2").addInput(theta_1.output(0)).addInput(theta_2_x_2.output(0)).build();
        graph.opBuilder("Add", "h_x").addInput(theta1Andtheta2x2.output(0)).addInput(theta_3_x_3.output(0)).build();
        return graph;
    }

    public static Object runGraph(Graph graph, Double x_2, Double x_3) {
        Object result;
        try (Session sess = new Session(graph)) {
            result = sess.runner().fetch("h_x").feed("x_2", Tensor.<Double>create(x_2, Double.class))
                    .feed("x_3", Tensor.<Double>create(x_3, Double.class)).run().get(0).expect(Double.class)
                    .doubleValue();
        }
        return result;
    }


    public static Double loadModel(Double x_2, Double x_3) {
        // get the current working directory for windows
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // this h_x is what defined in the python model not what we define in the above function.
        SavedModelBundle model = SavedModelBundle.load("C:\\Users\\hussen\\dev\\tensorFlowTest\\tensorFlowTest\\src\\main\\resources", "serve");
        Tensor<Double> tensor = model.session().runner().fetch("h_x").feed("x_2", Tensor.<Double>create(x_2, Double.class))
                .feed("x_3", Tensor.<Double>create(x_3, Double.class)).run().get(0).expect(Double.class);
        return tensor.doubleValue();
    }
    public static void main(String[] args) {
        // those are the two possibilities with tf in Java
        // 1- define the graph manually and execute it
        Double[] x = {1.0, -0.4460438603276164, -0.22609336757768828};
        Double[] theta = {340412.65957447, 109447.79558639, -6578.3539709 };
        Graph graph = TensorFlowGraph.createGraph(theta);
        Object graphResult = TensorFlowGraph.runGraph(graph, x[1], x[2]);
        graph.close();
        System.out.println("graph: " + graphResult);
        // 2- load a pre trained mode and execute it
        Object modelResult = loadModel(x[1], x[2]);
        System.out.println("model: " + modelResult);
    }
}
