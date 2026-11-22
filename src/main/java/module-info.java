module order.app {
    requires java.base;
    requires static lombok;
    requires javafaker;

    exports com.example.order.processing;
    exports com.example.order.storage;
    exports com.example.order.app;
}