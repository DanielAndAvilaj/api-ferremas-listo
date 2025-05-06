package cl.duoc.ferremas.controller;

import cl.duoc.ferremas.model.Producto;
import cl.duoc.ferremas.model.Sucursal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final List<Sucursal> sucursales = new ArrayList<>();
    private final List<Producto> productosGlobal;
    private final AtomicInteger sucursalIdCounter = new AtomicInteger(1);

    // Inyección simulada de lista de productos (compartida)
    public SucursalController(ProductoController productoController) {
        this.productosGlobal = productoController.getProductos(); // accede a la lista de productos existente

        // Datos de ejemplo
        Sucursal s1 = new Sucursal();
        s1.setId(sucursalIdCounter.getAndIncrement());
        s1.setNombre("Sucursal Centro");
        s1.setDireccion("Av. Principal 123");

        Sucursal s2 = new Sucursal();
        s2.setId(sucursalIdCounter.getAndIncrement());
        s2.setNombre("Sucursal Norte");
        s2.setDireccion("Calle Norte 456");

        sucursales.add(s1);
        sucursales.add(s2);
    }

    @GetMapping
    public List<Sucursal> listarSucursales() {
        return sucursales;
    }

    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody Sucursal nueva) {
        nueva.setId(sucursalIdCounter.getAndIncrement());
        sucursales.add(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Producto>> listarProductosDeSucursal(@PathVariable int id) {
        Optional<Sucursal> sucursal = sucursales.stream().filter(s -> s.getId() == id).findFirst();

        if (sucursal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String nombreSucursal = sucursal.get().getNombre();

        List<Producto> disponibles = productosGlobal.stream()
                .filter(p -> p.getSucursales().contains(nombreSucursal))
                .toList();

        return ResponseEntity.ok(disponibles);
    }
}
