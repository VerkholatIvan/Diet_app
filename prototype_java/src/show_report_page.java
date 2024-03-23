import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class show_report_page extends JPanel{

    protected App controller;
    protected String name="report page";
    protected std_label title;

    protected JRadioButton All;
    protected JRadioButton weekly;
    protected JRadioButton monthly;


    protected JButton back;

    protected JLabel total_intake;
    protected JLabel total_calories;
    protected JLabel total_protein;
    protected JLabel total_fat;
    protected JLabel total_sugar;

    JPanel evaluation_part;
    JPanel evaluation_content;
    protected JPanel chart_part;
    protected ChartPanel calories_chart_panel;
    protected ChartPanel nutrition_chart_panel;

    public show_report_page(){};

    public show_report_page(App controller){
        this.controller=controller;
    }
    public void initialise(){


        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));



        if (controller.user.food_history_daily.isEmpty()){

            JPanel first_part=new JPanel(new GridBagLayout());
            first_part.setBackground(Color.white);

            JPanel second_part=new JPanel(new GridBagLayout());
            second_part.setBackground(Color.white);

            GridBagConstraints grid=new GridBagConstraints();

            title=new std_label("Your data is empty",15);
            back=new std_button("Back",Color.white, Color.gray, 140, 30, 15);
            back.addActionListener(e -> controller.switch_page("Main_page"));

            grid.gridx=0;
            grid.gridy=0;
            first_part.add(title,grid);

            grid.gridy=1;
            first_part.add(back,grid);

            this.add(first_part);

        }
        else {

            this.add(Box.createRigidArea(new Dimension(100,30)));

            JPanel title_part=new JPanel(new GridBagLayout());
            title_part.setBackground(Color.white);

            JPanel first_part=new JPanel(new GridBagLayout());
            first_part.setBackground(Color.white);

            JPanel second_part=new JPanel(new GridBagLayout());
            second_part.setBackground(Color.white);

            evaluation_part=new JPanel(new GridBagLayout());
            evaluation_part.setBackground(Color.white);

            chart_part=new JPanel(new GridBagLayout());
            chart_part.setBackground(Color.white);

            JPanel last_part=new JPanel(new GridBagLayout());
            last_part.setBackground(Color.white);

            GridBagConstraints grid=new GridBagConstraints();
            grid.anchor=GridBagConstraints.LINE_START;
            grid.gridx=0;
            grid.gridy=0;
            first_part.add(Box.createRigidArea(new Dimension(100,30)),grid);

            title = new std_label("Your dietary report", 17);
            grid.gridx=0;
            grid.gridy=0;
            grid.insets=new Insets(0,0,10,0);
            title_part.add(title,grid);

            All=new JRadioButton("All");
            All.setFont(new Font("Avenir",Font.PLAIN,15));
            All.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    evaluation_content.removeAll();
                    generate_evaluation_content(controller.user.day_count);
                    evaluation_part.revalidate();
                    evaluation_part.repaint();

                    generate_text_report(controller.user.day_count);
                    chart_part.removeAll();
                    generate_charts(controller.user.day_count);
                    chart_part.revalidate();
                    chart_part.repaint();
                }
            });


            weekly=new JRadioButton("Weekly");
            weekly.setFont(new Font("Avenir",Font.PLAIN,15));
            weekly.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    evaluation_content.removeAll();
                    generate_evaluation_content(7);
                    evaluation_part.revalidate();
                    evaluation_part.repaint();

                    generate_text_report(7);
                    chart_part.removeAll();
                    generate_charts(7);
                    chart_part.revalidate();
                    chart_part.repaint();
                }
            });


            monthly=new JRadioButton("Monthly");
            monthly.setFont(new Font("Avenir",Font.PLAIN,15));
            monthly.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    evaluation_content.removeAll();
                    generate_evaluation_content(30);
                    evaluation_part.revalidate();
                    evaluation_part.repaint();

                    generate_text_report(30);
                    chart_part.removeAll();
                    generate_charts(30);
                    chart_part.revalidate();
                    chart_part.repaint();
                }
            });

            grid.gridx=0;
            grid.gridy=2;
            grid.insets=new Insets(0,0,30,0);
            first_part.add(weekly,grid);
            grid.gridx=1;
            first_part.add(monthly,grid);
            grid.gridx=2;
            first_part.add(All,grid);

            ButtonGroup duration=new ButtonGroup();
            duration.add(All);
            duration.add(weekly);
            duration.add(monthly);

            weekly.setSelected(true);


            total_intake=new std_label("",14);
            grid.gridy=2;
            grid.insets=new Insets(0,0,20,0);
            second_part.add(total_intake,grid);

            total_calories=new std_label("",14);
            grid.gridy=3;
            second_part.add(total_calories,grid);

            total_protein=new std_label("",14);
            grid.gridy=4;
            second_part.add(total_protein,grid);

            total_fat=new std_label("",14);
            grid.gridy=5;
            second_part.add(total_fat,grid);

            total_sugar=new std_label("",14);
            grid.gridy=6;
            second_part.add(total_sugar,grid);

            grid.gridy=7;

            this.generate_text_report(7);
            this.generate_charts(7);
            this.generate_evaluation_content(7);



            grid.gridx=0;
            grid.gridy=0;
            grid.anchor=GridBagConstraints.CENTER;
            last_part.add(Box.createRigidArea(new Dimension(100,40)),grid);
            back=new std_button("Back",Color.white, Color.gray, 140, 30, 15);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    controller.switch_page("Main_page");

                }
            });

            grid.gridy=1;
            last_part.add(back,grid);

            std_button Export =new std_button("Export PDF",Color.white, Color.gray, 140, 30, 15);
            Export.addActionListener(e->export_pdf());
            grid.gridy=2;
            last_part.add(Export,grid);


            title_part.setMaximumSize(new Dimension(Integer.MAX_VALUE,35));
            first_part.setMaximumSize(new Dimension(Integer.MAX_VALUE,130));
            second_part.setMaximumSize(new Dimension(Integer.MAX_VALUE,240));

            this.add(title_part);
            this.add(first_part);
            this.add(second_part);
            this.add(Box.createRigidArea(new Dimension(100,50)));
            this.add(evaluation_part);
            this.add(chart_part);
            this.add(last_part);

        }

    }

    protected void generate_evaluation_content(int day){

        Vector<String>content=new Vector<>(controller.user.evaluate(day));


        this.evaluation_content=new JPanel(new GridBagLayout());
        evaluation_content.setBackground(Color.white);
        GridBagConstraints grid=new GridBagConstraints();
        grid.gridx=0;
        grid.anchor=GridBagConstraints.LINE_START;


        grid.gridy=1;
        std_label conclusion=new std_label("Conclusion:",14);
        evaluation_content.add(Box.createRigidArea(new Dimension(100,30)),grid);
        evaluation_content.add(conclusion,grid);

        for (int i=0;i<content.size();i++){
            std_label temp=new std_label(content.get(i),14);
            grid.gridy=i+2;
            this.evaluation_content.add(temp,grid);

        }
        grid.gridx=0;

        //evaluation_part.add(Box.createRigidArea(new Dimension(100,50)),grid);
        grid.gridy=0;
        this.evaluation_part.add(this.evaluation_content,grid);
        grid.gridy=2;
        evaluation_part.add(Box.createRigidArea(new Dimension(100,50)),grid);


    }

    protected void generate_charts(int day){

        JFreeChart calories_chart=ChartFactory.createLineChart(

                "Calories",
                "Day",
                "Kcal",
                plot_calories(day),
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        calories_chart.setBackgroundPaint(Color.white);
        calories_chart.getTitle().setFont(new Font("Avenir",Font.PLAIN,15));

        CategoryPlot plot = (CategoryPlot) calories_chart.getPlot();

        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setLabelFont(new Font("Avenir",Font.PLAIN,12));

        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setLabelFont(new Font("Avenir",Font.PLAIN,12));

        xAxis.setTickLabelFont(new Font("Avenir",Font.PLAIN,7));
        yAxis.setTickLabelFont(new Font("Avenir",Font.PLAIN,7));


        this.calories_chart_panel=new ChartPanel(calories_chart);
        this.calories_chart_panel.setPreferredSize(new Dimension(600, 400));


        GridBagConstraints grid=new GridBagConstraints();
        grid.gridx=0;
        grid.gridy=0;
        this.chart_part.add(calories_chart_panel,grid);


        JFreeChart nutrition_chart=ChartFactory.createLineChart(

                "Nutrition in take",
                "Day",
                "Gram",
                create_dataset(day),
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        nutrition_chart.setBackgroundPaint(Color.white);
        nutrition_chart.getTitle().setFont(new Font("Avenir",Font.PLAIN,15));

        CategoryPlot plot2 = (CategoryPlot) nutrition_chart.getPlot();

        CategoryAxis xAxis2 = plot.getDomainAxis();
        xAxis.setLabelFont(new Font("Avenir",Font.PLAIN,12));

        ValueAxis yAxis2 = plot.getRangeAxis();
        yAxis.setLabelFont(new Font("Avenir",Font.PLAIN,12));

        xAxis.setTickLabelFont(new Font("Avenir",Font.PLAIN,7));
        yAxis.setTickLabelFont(new Font("Avenir",Font.PLAIN,7));


        this.nutrition_chart_panel=new ChartPanel(nutrition_chart);
        this.nutrition_chart_panel.setPreferredSize(new Dimension(600, 400));

        grid.gridx=0;
        grid.gridy=1;

        this.chart_part.add(nutrition_chart_panel,grid);



    }

    protected DefaultCategoryDataset plot_calories(int day){

        DefaultCategoryDataset data=new DefaultCategoryDataset();
        int day_label=1;

        if (day>controller.user.day_count){
            int gap=day-controller.user.day_count;
            day_label=gap+1;
            for (int i=1;i<=gap;i++){

                data.addValue(0,"Calories","Day"+i);

            }

        }

        for (ArrayList<Food> i:controller.user.food_history_daily){

            double daily_calories=0;

            for (Food j:i){
                daily_calories+=j.Energy;
            }


            data.addValue(daily_calories,"Calories","Day"+day_label);

            if (day_label==day) break;

            day_label++;

        }

        return data;

    }

    protected DefaultCategoryDataset create_dataset(int day){

        DefaultCategoryDataset data=new DefaultCategoryDataset();

        int day_label=1;

        if (day>controller.user.day_count){
            int gap=day-controller.user.day_count;
            day_label=gap+1;
            for (int i=1;i<=gap;i++){

                data.addValue(0,"Protein","Day"+i);
                data.addValue(0,"Fat","Day"+i);
                data.addValue(0,"Sugar","Day"+i);
            }

        }

        for (ArrayList<Food> i:controller.user.food_history_daily){

            double daily_protein=0;
            double daily_fat=0;
            double daily_sugar=0;

            for (Food j:i){
                daily_protein+=j.Protein;
                daily_fat+=j.Lipid;
                daily_sugar+= j.Sugar;
            }


            data.addValue(daily_protein,"Protein","Day"+day_label);
            data.addValue(daily_fat,"Fat","Day"+day_label);
            data.addValue(daily_sugar,"Sugar","Day"+day_label);

            day_label++;

        }

        return data;
    }

    protected void generate_text_report(int day){

        total_intake.setText("Your total intake in the past "+day+" day(s): ");

        if (day>controller.user.day_count) day=controller.user.day_count;

        total_calories.setText("Energy: "+String.format("%.2f",controller.user.total_energy(day))+"kcal, "+String.format("%.2f",controller.user.total_energy(day)/day)+"kcal in average per day.");
        total_protein.setText("Protein: "+String.format("%.2f",controller.user.total_protein(day))+"g, "+String.format("%.2f",controller.user.total_protein(day)/day)+"g in average per day.");
        total_fat.setText("Fat: "+String.format("%.2f",controller.user.total_fat(day))+"g, "+String.format("%.2f",controller.user.total_fat(day)/day)+"g in average per day.");
        total_sugar.setText("Sugar: "+String.format("%.2f",controller.user.total_sugar(day))+"g, "+String.format("%.2f",controller.user.total_sugar(day)/controller.user.day_count)+"g in average per day.");

    }

    protected void export_pdf(){



        BufferedImage image = new BufferedImage(controller.report_page.getSize().width,controller.report_page.getSize().height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // paint the content to bufferedImage
        controller.report_page.paintAll(g2d);
        g2d.dispose();


        // PDF generation
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            ImageData imageData = ImageDataFactory.create(baos.toByteArray());
            Image pdfImg = new Image(imageData);

            // create pdf and add image
            PdfWriter writer = new PdfWriter("Report.pdf");
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);
            doc.add(pdfImg);
            doc.close();
            JOptionPane.showMessageDialog(null, "PDF report created. Please check the project folder. ", null, JOptionPane.INFORMATION_MESSAGE);
            System.out.println("PDF created.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
