package main.webapp.model.service;

import com.google.gson.Gson;
import main.webapp.model.dao.ISaleDetailDao;
import main.webapp.model.dao.SaleDetailDao;
import main.webapp.model.entity.SaleDetailEntity;

import java.io.BufferedReader;
import java.util.List;

public class DetailMasterService implements IDetailMasterService {
    @Override
    public String saveDetailMaster(BufferedReader reader) {
        Gson gson = new Gson();
        SaleDetailEntity entity = gson.fromJson(reader, SaleDetailEntity.class);

        ISaleDetailDao dao = new SaleDetailDao();
        List<SaleDetailEntity> listDetail = dao.saveSale(entity);

        String json = gson.toJson(listDetail);

        System.out.println(json);

        return json;
    }

    @Override
    public String deleteDetailM(String keySale, int idSaleDetail) {
        Gson gson = new Gson();

        ISaleDetailDao dao = new SaleDetailDao();

        String json = gson.toJson(dao.deleteDetail(idSaleDetail,keySale));

        return json;
    }
}
