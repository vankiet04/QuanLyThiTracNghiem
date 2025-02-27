/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class BaiThi {
    private String tenBaiThi;
    private int soLuotLam;
    private double diem;

    public BaiThi(String tenBaiThi, int soLuotLam, double diem) {
        this.tenBaiThi = tenBaiThi;
        this.soLuotLam = soLuotLam;
        this.diem = diem;
    }

    public String getTenBaiThi() { return tenBaiThi; }
    public int getSoLuotLam() { return soLuotLam; }
    public double getDiem() { return diem; }
}
