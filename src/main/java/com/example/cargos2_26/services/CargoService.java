package com.example.cargos2_26.services;

import com.example.cargos2_26.repos.CargoRepository;
import com.example.cargos2_26.entity.Cargos;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CargoService {
    @Autowired
    private CargoRepository repo;

    public List<Cargos> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public List<Cargos> filterBySendingDate(java.sql.Date date) {
        return repo.filterBySendingDate(date);
    }

    public List<Cargos> filterByArrivalDate(java.sql.Date date) {
        return repo.filterByArrivalDate(date);
    }

    public void save(Cargos cargos) {
        repo.save(cargos);
    }

    public Cargos get(Long id) {
        return repo.findById(Math.toIntExact(id)).get();
    }

    public void delete(Long id) {
        repo.deleteById(Math.toIntExact(id));
    }

    public String generateBarChart_send() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Object[]> cargoSendData = repo.countByDayChart_send();

        for (Object[] data : cargoSendData) {
            String date = data[0].toString();
            Long count = (Long) data[1];
            dataset.addValue(count, "Кол-во", date);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Количество грузов по дням отправки",
                "Даты",
                "Количество грузов",
                dataset
        );

        String path = "src/main/resources/static/images/bar_chart_send.png";
        ChartUtils.saveChartAsPNG(new File(path), chart, 800, 600);
        return "/images/bar_chart_send.png";
    }

    public String generateBarChart_arr() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Object[]> cargoArrData = repo.countByDayChart_arr();

        for (Object[] data : cargoArrData) {
            String date = data[0].toString();
            Long count = (Long) data[1];
            dataset.addValue(count, "Кол-во", date);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Количество грузов по дням прибытия",
                "Даты",
                "Количество грузов",
                dataset
        );

        String path = "src/main/resources/static/images/bar_chart_arr.png";
        ChartUtils.saveChartAsPNG(new File(path), chart, 800, 600);
        return "/images/bar_chart_arr.png";
    }


}
