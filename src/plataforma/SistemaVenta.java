package plataforma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import usuarios.*;
import java.util.Scanner;
import pedido.*;
import productos.CategoriaProducto;
import productos.Producto;
import productos.ProductoComprado;

public class SistemaVenta {

    private ArrayList<Usuario> usuarios;
    private static ArrayList<Pedido> pedidos;

    public SistemaVenta() {
        pedidos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public static ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void inicializarDatos() {
        //-2.189327,-79.889532 malecon 2000     -2.1475365, -79.9644709 espol      -2.1546187, -79.8928285 mall
        //-2.1275775, -79.9078251 riocentro norte       -2.1668044, -79.9457075 ceibos      -2.117308, -79.8930216 guayacanes
        Producto p1 = new Producto("Carne roja", "001", "Carne fresca de vaca", CategoriaProducto.carnicos, 12.5, "Prov1");
        Producto p2 = new Producto("Pollo", "002", "1/4 de pollo", CategoriaProducto.carnicos, 10.5, "Prov1");
        Producto p3 = new Producto("Leche", "003", "Leche fresca de vaca", CategoriaProducto.lacteos, 3, "Prov1");
        ArrayList<Producto> pp1 = new ArrayList<>();
        pp1.add(p1);
        pp1.add(p2);
        pp1.add(p3);
        usuarios.add(new Proveedor("Proveedor 1", "0998877665", "luidbrav@espol.edu.ec", "Villa Vieja Av. 434 Mz. 32", new Coordenada(-2.189327, -79.889532), "Prov1", "123", "2232521", pp1));
        Producto p4 = new Producto("Uva", "005", "1 libra de uva roja", CategoriaProducto.frutas, 2.5, "Prov2");
        Producto p5 = new Producto("Helado", "004", "Helado de chocolate casero", CategoriaProducto.conservas, 1.5, "Prov2");
        Producto p6 = new Producto("Pimiento", "006", "Pimiento rojo fresco", CategoriaProducto.vegetales, 0.5, "Prov2");
        ArrayList<Producto> pp2 = new ArrayList<>();
        pp2.add(p4);
        pp2.add(p5);
        pp2.add(p6);
        usuarios.add(new Proveedor("Proveedor 2", "0998877677", "diiszamb@espol.edu.ec", "Guayacanes 8", new Coordenada(-2.117308, -79.8930216), "Prov2", "123", "22222222", pp2));
        Producto p7 = new Producto("Sandia", "007", "La mas sabrosa del mercado", CategoriaProducto.frutas, 3, "Prov3");
        Producto p8 = new Producto("Helado", "008", "Helado de vainilla casero", CategoriaProducto.conservas, 1.5, "Prov3");
        Producto p9 = new Producto("Brocoli", "009", "El brocoli mas fresco", CategoriaProducto.vegetales, 1.5, "Prov3");
        ArrayList<Producto> pp3 = new ArrayList<>();
        pp3.add(p7);
        pp3.add(p8);
        pp3.add(p9);
        usuarios.add(new Proveedor("Proveedor 3", "0998877555", "davagale@espol.edu.ec", "Ceibos", new Coordenada(-2.1668044, -79.9457075), "Prov3", "123", "33333333", pp3));
        usuarios.add(new Cliente("Cliente 1", "0111", "luisdonoso2001@gmail.com", "Guayacanes 8", new Coordenada(-2.117308, -79.8930216), "cliente1", "321", new PagoTarjeta("VISA", "123456789123", "Cliente 1")));
    }

    public boolean registrarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (!(u.getNombreUsuario().equals(usuario.getNombreUsuario()))) {
                usuarios.add(usuario);
                return true;
            }
        }
        return false;
    }

    public void iniciarSesion(String nombreUsuario, String clave) {
        boolean v = false;
        Scanner sn = new Scanner(System.in);
        String opcion = "";
        for (Usuario u : usuarios) {
            if ((u.getNombreUsuario().equals(nombreUsuario)) && (u.getClave().equals(clave))) {
                v = true;
                if (u instanceof Proveedor) {
                    Proveedor pp=(Proveedor) u;
                    do{
                        System.out.println("Opciones que puede realizar como proveedor");
                        System.out.println("");
                        System.out.println("A.- Registar Productos");
                        System.out.println("B.- Consultar informacion de los pedidos");
                        System.out.println("C.- Gestionar estado de los pedidos");
                        System.out.println("D.- Consultar y editar informacion de los productos registrados");
                        System.out.println("E.- Cerrar sesión");
                        System.out.print("Ingrese una opción para continuar: ");
                        opcion = sn.next();
                        switch (opcion.toUpperCase()) {
                            case "A":
                                registrarProducto(pp);
                                break;
                            case "B":
                                pp.consultarInformacion(buscarPedidos(pp));
                                break;
                            case "C":
                                System.out.print("Ingrese el codigo del pedido: ");
                               String co=sn.nextLine();
                               Pedido ppp=buscarPedido(co);
                               if(ppp!=null){
                               pp.gestionarEstadoPedido(ppp);
                               }else{
                                   System.out.print("No existe el pedido");
                               break;
                               }
                                break;
                            case "D":
                                System.out.println("Filtrar prducto");
                                System.out.println("1. Consultar por nombre");
                                System.out.println("2. Consultar por categoria");
                                String o= sn.nextLine();
                                ArrayList<Producto> produc = null;
                                if (o.equals("1")) {
                                    System.out.println("Ingrese nombre: ");
                                    String nombre = sn.nextLine();
                                    produc = pp.buscarProductosN(nombre);
                                } else if (o.equals("2")) {
                                    System.out.println("Ingrese categoria: ");
                                    String cat = sn.nextLine();
                                    produc = pp.buscarProductosC(cat);
                                }
                                if(produc.isEmpty()){
                                    System.out.println("Lista vacía, vuelva a filtrar datos");
                                    break;
                                }
                                for (Producto e : produc) {
                                    System.out.println("Producto: "+e.getCodigo()+", nombre: "+e.getNombre()+", categoria: "+e.getCategoria().toString());
                                }
                                System.out.println("Ingrese codigo a editar");
                                String CJ= sn.nextLine();
                                editarProducto(CJ,pp);
                                break;
                            case "E":
                                System.out.println("\nCerrando sesión...");
                                break;
                            default:
                                System.out.println("Esta no es una opción. Ingrese otra");
                        }
                    }while(!opcion.toUpperCase().equals("E"));
                } else {
                    Cliente cc=(Cliente) u;
                    do{
                        System.out.println("Opciones que puede realizar como comprador");
                        System.out.println("");
                        System.out.println("A.- Consultar infomacion de productos");
                        System.out.println("B.- Consultar productos en el carrito de compras");
                        System.out.println("C.- Cerrar sesión");
                        System.out.print("Ingrese una opción para continuar: ");
                        opcion = sn.next();
                        switch (opcion.toUpperCase()) {
                            case "A":
                                String no="";
                                while(!no.equalsIgnoreCase("no")){
                                filtrarProductos(cc);
                                System.out.print("Desea realizar otra búsqueda? si/no");
                                sn.nextLine();
                                no=sn.nextLine();
                                }
                                System.out.print("Ingrese el código del producto a comprar");
                                String code=sn.nextLine();
                                Producto prodC=null;
                                for(Proveedor p: listaProveedores()){
                                    prodC=p.buscarProducto(code);
                                    if(prodC!=null){break;}
                               }
                                if(prodC!=null){
                                System.out.println("\nProducto elegido: "+prodC);
                                System.out.println("\nDesea agregarlo al carrito de compras?: si/no");
                                String var=sn.nextLine();
                                if(var.equalsIgnoreCase("si")){
                                System.out.println("Cuántos desea adquirir? ");
                                int cn=sn.nextInt();
                                sn.nextLine();
                                cc.getCarritoCompra().getProductosRegistrados().add(new ProductoComprado(prodC,cn));
                                }
                                }
                                System.out.println("COMPRA EXITOSA");
                                break;
                            case "B":
                                String si="";
                                sn.nextLine();
                                while(!si.equalsIgnoreCase("no")){
                                cc.getCarritoCompra().mostarInfoProductos();
                                System.out.println("Desea eliminar algún producto? ");
                                si=sn.nextLine();
                                if(si.equalsIgnoreCase("si")){
                                System.out.println("Ingrese codigo a eliminar");
                                String cd=sn.nextLine();
                                cc.eliminarProducto(cd);
                                }}
                                System.out.println("Desea comprar los productos?");
                                String si2=sn.nextLine();
                                if(si2.equalsIgnoreCase("si")){
                               if(cc.getDatosFormaPago().procesarpago(cc.getCarritoCompra().totalPagar(), cc)){
                                for(String n: cc.getCarritoCompra().cantidadProveedores()){
                                    ArrayList<ProductoComprado> p=cc.getCarritoCompra().productosporProveedor(n);
                                    int cnu=1;
                                    String codigo="pedido"+cnu;
                                    while(existeNombre(codigo)){
                                        cnu++;
                                        codigo="pedido"+cnu;
                                     }
                               Pedido pedidoF=new Pedido(cc,codigo,p);
                               pedidos.add(pedidoF);
                                }
                                cc.enviarConGMail("COMPRA", "Los datos de la compra son </br>"+cc.getCarritoCompra());
                               }else{
                               System.out.println("No se pudo procesar el pago.");
                               break;
                               }
                                }else{
                                System.out.println("Volviendo al menú...");}
                                break;
                            case "C":
                                mostrarPedidos(cc);
                                break;
                            case "D":
                                System.out.println("\nCerrando sesión...");
                                break;
                            default:
                                System.out.println("Esta no es una opción. Ingrese otra");
                        }
                    }while(!opcion.toUpperCase().equals("D"));
                }
            }
        }
        if(!v){
        System.out.println("Credenciales invalidas");
        }
    }

    /**
     * Este método retorna true si el usuario existe y false si el usuario no
     * existe.
     *
     * @param u Nombre del usuario
     * @return boolean si el usuario existe o no
     */
    public boolean comprobarUsuario(String u) {
        try {
            if (usuarios.stream().anyMatch((v) -> ((u).equals(v.getNombreUsuario())))) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
    
    public ArrayList<Pedido> buscarPedidos(Proveedor f) {
        ArrayList<Pedido> p=new ArrayList<>();
        for (Pedido a : pedidos) {
            if (a.getProveedor().equals(f.getNombreUsuario())) {
                p.add(a);
            }
        }
        return p;
    }
    
        public Pedido buscarPedido(String codigo) {
        for (Pedido a : pedidos) {
            if (a.getCodigo().equals(codigo)) {
                return a;
            }
        }
        return null;
    }
        
     public ArrayList<Proveedor> listaProveedores() {
       ArrayList<Proveedor> proveedores=new ArrayList<>();
         for (Usuario a : usuarios) {
            if (a instanceof Proveedor) {
                proveedores.add ((Proveedor)a);
            }
        }
        return proveedores;
    }
    public boolean existeNombre(String codigo) {
        for (Pedido a : pedidos) {
            if (a.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
        
        
    public void mostrarPedidos(Cliente cliente) {
        for (Pedido p : pedidos) {
            if (p.getDatosCliente().getNombreUsuario().equals(cliente.getNombreUsuario())) {
                if (p.getDatosCliente().getDatosFormaPago() instanceof PagoTarjeta) {
                    PagoTarjeta tarjeta = (PagoTarjeta) p.getDatosCliente().getDatosFormaPago();
                    System.out.println("codigo:" + p.getCodigo() + " fecha:" + p.getFechaInicio()
                            + " costo:" + p.getCostoPedido() + " Forma de pago: Tarjeta  Numero:" + tarjeta.getNumeroTarjeta()
                            + "listaProductos: " + p.getProductosPedidos() + "Estado del Pedido:" + p.getEstadoPedido());
                } else {
                    System.out.println("codigo:" + p.getCodigo() + " fecha:" + p.getFechaInicio()
                            + " costo:" + p.getCostoPedido() + " Forma de pago: PayPal listaProductos: " + p.getProductosPedidos()
                            + "Estado del Pedido:" + p.getEstadoPedido());

                }

            }
        }
    }

    private void registrarProducto(Proveedor u) {
        Scanner sn = new Scanner(System.in);
        System.out.println("\nINGRESO DE DATOS DEL PRODUCTO");
        System.out.print("Nombre del producto: ");
        String nombre = sn.nextLine();
        System.out.print("Código: ");
        String c = sn.nextLine();
        System.out.print("Descripción pequeña: ");
        String d = sn.nextLine();
        System.out.println("Categorías disponibles: ");
        System.out.println(Arrays.toString(CategoriaProducto.values()));
        System.out.print("Categoría del producto: ");
        String cat = sn.nextLine();
        System.out.print("Precio Unitario del producto: ");
        double p = sn.nextDouble();
        sn.nextLine();
        Producto producto=new Producto(nombre,c,d,CategoriaProducto.valueOf(cat.toLowerCase()),p,u.getNombreUsuario());
        u.registrarProducto(producto);
    }

    private void editarProducto(String CJ, Proveedor pp) {
         Scanner sn = new Scanner(System.in);
        Producto pB= pp.buscarProducto(CJ);
        if(pB!=null){
            String oo="";
            while(!oo.equalsIgnoreCase("e"))
            System.out.println("EDITAR");
            System.out.println("a. Nombre");
            System.out.println("b. Descripcion");
            System.out.println("c. Categoría");
            System.out.println("d. Precio");
            System.out.println("e. Volver");
            System.out.println("Ingrese la opción a editar: ");
            oo=sn.next();
            switch(oo.toLowerCase()){
                case "a":
                    System.out.println("Ingrese el nuevo nombre: ");
                    String n=sn.nextLine();
                    pB.setNombre(n);
                    break;
                case "b":
                    System.out.println("Ingrese la nueva descripcion: ");
                    String d=sn.nextLine();
                    pB.setDescripcion(d);
                    break;  
                case "c":
                    System.out.println("Ingrese la nueva categoria: ");
                    System.out.println(Arrays.toString(CategoriaProducto.values()));
                    String c=sn.nextLine();
                    pB.setCategoria(CategoriaProducto.valueOf(c.toLowerCase()));
                    break;
                case "d":
                    System.out.println("Ingrese el nuevo precio: ");
                    System.out.println(Arrays.toString(CategoriaProducto.values()));
                    double p=sn.nextDouble();
                    pB.setPrecio(p); 
                    break;
                case "e":
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("No es opcion");
                    break;
            }
        }else{
        System.out.print("No se encuentra este producto");
        }
    }

    private void filtrarProductos(Cliente cc) {
        Scanner sn = new Scanner(System.in);
        String opcion="6";
        while(!opcion.equals("5")){
               System.out.println("Filtrar producto");
               System.out.println("1. Consultar por nombre");
               System.out.println("2. Consultar por categoria");
               System.out.println("3. Consultar por rango de precio");
               System.out.println("4. Consultar por nombre y categoria");
               System.out.println("5. Salir");
               System.out.println(Arrays.toString(CategoriaProducto.values()));
               System.out.println("Ingrese la opción. "
                       + "\nSi no ingresa una opción válida mostrará filtrados solo por ubicación");
               opcion=sn.nextLine();
               ArrayList<Producto> produc = cc.consultarProductos(listaProveedores());
               if (opcion.equals("1")) {
                    System.out.println("Ingrese nombre: ");
                    String nombre = sn.nextLine();
                    produc = cc.consultarProductos(listaProveedores(), nombre);
                } else if (opcion.equals("2")) {
                    System.out.println("Ingrese categoria: ");
                    String nombre = sn.nextLine();
                    produc = cc.consultarProductosC(listaProveedores(), nombre);
                }else if (opcion.equals("3")) {
                    System.out.println("Ingrese precio inicial: ");
                    double pmin = sn.nextDouble();
                    System.out.println("Ingrese precio final: ");
                    double pmax = sn.nextDouble();
                    sn.nextLine();
                    produc = cc.consultarProductos(listaProveedores(), pmin, pmax);
                }else if (opcion.equals("4")) {
                    System.out.println("Ingrese nombre: ");
                    String nombre = sn.nextLine();
                    System.out.println("Ingrese categoria: ");
                    String cat = sn.nextLine();
                    produc = cc.consultarProductos(listaProveedores(), nombre, cat);
                }else if (opcion.equals("5")) {
                    System.out.println("Volver....");
                    break;
                }
                if(produc.isEmpty()){
                System.out.println("Lista vacía, vuelva a filtrar datos");
                }else{
                for (Producto e : produc) {
                System.out.println(e);}
                }
                }
    }

}