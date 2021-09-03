package com.example.autofine.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarData {


    @SerializedName("id")
    @Expose
    private Long  id;

    @SerializedName("plateNumber")
    @Expose
    private  String  plateNumber;

    @SerializedName("seriesNumberDocument")
    @Expose
    private  String  seriesNumberDocument;



    public Long getId() {return id; }

    public void setId(Long id) { this.id = id;  }

    public String getPlateNumber() {  return plateNumber;  }

    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber;  }

    public String getSeriesNumberDocument() { return seriesNumberDocument; }

    public void setSeriesNumberDocument(String seriesNumberDocument) { this.seriesNumberDocument = seriesNumberDocument; }
}
