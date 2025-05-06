package cl.duoc.ferremas.model;

import java.util.List;

public class ProductoPatchDTO {
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
    private List<String> sucursales;

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public List<String> getSucursales() { return sucursales; }
    public void setSucursales(List<String> sucursales) { this.sucursales = sucursales; }
}
