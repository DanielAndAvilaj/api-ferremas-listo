package cl.duoc.ferremas.controller;

import cl.duoc.ferremas.model.Producto;
import cl.duoc.ferremas.model.ProductoPatchDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public ProductoController() {
        // Productos de prueba inicial
        Producto p1 = new Producto();
        p1.setId(idCounter.getAndIncrement());
        p1.setNombre("Martillo de acero");
        p1.setCategoria("herramientas");
        p1.setPrecio(45.99);
        p1.setStock(30);
        p1.setSucursales(List.of("Sucursal Centro", "Sucursal Norte"));

        Producto p2 = new Producto();
        p2.setId(idCounter.getAndIncrement());
        p2.setNombre("Destornillador Philips");
        p2.setCategoria("herramientas");
        p2.setPrecio(12.50);
        p2.setStock(100);
        p2.setSucursales(List.of("Sucursal Centro"));

        productos.add(p1);
        productos.add(p2);
    }

    @GetMapping
public List<Producto> listarProductos(
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false, name = "precio_minimo") Double precioMinimo,
        @RequestParam(required = false, name = "precio_maximo") Double precioMaximo,
        @RequestParam(required = false, name = "stock_minimo") Integer stockMinimo,
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String sucursal
) {
    return productos.stream()
            .filter(p -> categoria == null || p.getCategoria().equalsIgnoreCase(categoria))
            .filter(p -> precioMinimo == null || p.getPrecio() >= precioMinimo)
            .filter(p -> precioMaximo == null || p.getPrecio() <= precioMaximo)
            .filter(p -> stockMinimo == null || p.getStock() >= stockMinimo)
            .filter(p -> nombre == null || p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .filter(p -> sucursal == null || p.getSucursales().stream()
                    .anyMatch(s -> s.equalsIgnoreCase(sucursal)))
            .toList();
}


    @PostMapping
    public Producto crearProducto(@RequestBody Producto nuevo) {
        nuevo.setId(idCounter.getAndIncrement());
        productos.add(nuevo);
        return nuevo;
    }

    @GetMapping("/{id}")
public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
    return productos.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

@PutMapping("/{id}")
public ResponseEntity<Producto> actualizarProductoCompleto(@PathVariable int id, @RequestBody Producto datosNuevos) {
    for (int i = 0; i < productos.size(); i++) {
        if (productos.get(i).getId() == id) {
            datosNuevos.setId(id); // Mantenemos el ID original
            productos.set(i, datosNuevos);
            return ResponseEntity.ok(datosNuevos);
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

@PatchMapping("/{id}")
public ResponseEntity<Producto> actualizarParcialProducto(@PathVariable int id, @RequestBody ProductoPatchDTO cambios) {
    for (Producto p : productos) {
        if (p.getId() == id) {
            if (cambios.getNombre() != null) p.setNombre(cambios.getNombre());
            if (cambios.getCategoria() != null) p.setCategoria(cambios.getCategoria());
            if (cambios.getPrecio() != null) p.setPrecio(cambios.getPrecio());
            if (cambios.getStock() != null) p.setStock(cambios.getStock());
            if (cambios.getSucursales() != null) p.setSucursales(cambios.getSucursales());
            return ResponseEntity.ok(p);
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
    boolean eliminado = productos.removeIf(p -> p.getId() == id);
    if (eliminado) {
        return ResponseEntity.noContent().build(); // 204 No Content
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 si no existe
    }
}

public List<Producto> getProductos() {
    return productos;
}

}
