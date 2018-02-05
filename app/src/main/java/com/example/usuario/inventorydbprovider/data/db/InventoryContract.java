package com.example.usuario.inventorydbprovider.data.db;

import android.provider.BaseColumns;

import java.util.HashMap;

/**
 *
 * No se puede instanciar ni heredar de ella
 */
public final class InventoryContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";
    //Constructor privado para que no sea instanciada
    private InventoryContract() { }

    //Por cada tabla se crea una clase que implementa la interfaz BaseColumns
    //y garantiza que ya exista la columna id
    public static class DependencyEntry implements BaseColumns {

        public static final String TABLE_NAME = "dependency";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SHORTNAME = "shortname";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGENAME = "imageName";

        //En el mismo orden que las declaramos
        public static final String[] ALL_COLUMNS = new String[] {
                BaseColumns._ID, COLUMN_NAME, COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION, COLUMN_IMAGENAME
        };
        public static final String WHERE_ID = String.format(
                "%s = ?",
                BaseColumns._ID
        );
        public static final String WHERE_NAME_AND_SHORTNAME = String.format(
                "%s = ? AND %s = ?",
                COLUMN_NAME,
                COLUMN_SHORTNAME
        );

        public static final String DEFAULT_SORT = COLUMN_NAME;

        //Hay dos opciones
        public static final String SQL_CREATE_ENTRIES = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_IMAGENAME
        );
        public static final String SQL_DELETE_ENTRIES = String.format(
                "DROP TABLE IF EXISTS %s", TABLE_NAME);
        public static final String SQL_INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s'),", TABLE_NAME,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_IMAGENAME,
                "AUla de 2CFGS",
                "2CFGS",
                "Aula de los resopladores de 2CFGS",
                "sin imagen"
        ) + String.format("('%s', '%s', '%s', '%s'),",
                "Aula de 1CFGS",
                "1CFGS",
                "Aula de los pardillos de 1CFGS",
                "sin imagen"
        ) + String.format("('%s', '%s', '%s', '%s'),",
                "Aula de TEST",
                "1CFGS",
                "Aula de los pardillos de 1CFGS",
                "sin imagen"
        ) + String.format("('%s', '%s', '%s', '%s'),",
                "Aula de TEST",
                "1CFGS",
                "Aula de los pardillos de 1CFGS",
                "sin imagen"
        ) + String.format("('%s', '%s', '%s', '%s'),",
                "Aula de TEST",
                "1CFGS",
                "Aula de los pardillos de 1CFGS",
                "sin imagen"
        ) + String.format("('%s', '%s', '%s', '%s')",
                "Aula de TEST",
                "1CFGS",
                "Aula de los pardillos de 1CFGS",
                "sin imagen"
        );

    }

    public static class SectorEntry implements BaseColumns {

        public static final String TABLE_NAME = "sector";
        public static final String COLUMN_DEPENDENCY_ID = "dependencyId";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SHORTNAME = "shortname";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGENAME = "imageName";

        public static final String REFERENCES_DEPENDENCY_ID = String.format(
                "FOREIGN KEY (%s) REFERENCES %s(%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COLUMN_DEPENDENCY_ID, DependencyEntry.TABLE_NAME, BaseColumns._ID
        );

        //En el mismo orden que las declaramos
        public static final String[] ALL_COLUMNS = new String[] {
                BaseColumns._ID,
                COLUMN_DEPENDENCY_ID,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_IMAGENAME
        };

        public static final String DEFAULT_SORT = COLUMN_NAME;

        //Hay dos opciones
        public static final String SQL_CREATE_ENTRIES = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_DEPENDENCY_ID,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_IMAGENAME,
                REFERENCES_DEPENDENCY_ID
        );
        public static final String SQL_DELETE_ENTRIES = String.format(
                "DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ", TABLE_NAME,
                COLUMN_DEPENDENCY_ID,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_IMAGENAME
        ) + String.format("('%s', '%s', '%s', '%s', '%s'),",
                1,
                "Armario Izquierda del aula de DAM1",
                "ArmIzqDAM1",
                "Armario del aula de DAM1 que se encuentra a la izquierda de la puerta",
                "sin imagen"
        ) + String.format("('%s', '%s', '%s', '%s', '%s')",
                2,
                "Armario Izquierda del aula de DAM2",
                "ArmIzqDAM2",
                "Armario del aula de DAM2 que se encuentra a la izquierda de la puerta",
                "sin imagen"
        );
    }

    public static class ProductViewEntry implements BaseColumns {

        public static final String TABLE_NAME = "product";
        public static final String COLUMN_SERIAL = "serial";
        public static final String COLUMN_MODEL_CODE = "modelCode";
        public static final String COLUMN_SHORTNAME = "shortname";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CATEGORY_ID = "category";
        public static final String COLUMN_CATEGORY_NAME = "categoryName";
        public static final String COLUMN_PRODUCT_CLASS_ID = "productClass";
        public static final String COLUMN_PRODUCT_CLASS_DESCRIPTION = "productClassDescription";
        public static final String COLUMN_SECTOR_ID = "sector";
        public static final String COLUMN_SECTOR_NAME = "sectorName";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_VENDOR = "vendor";
        public static final String COLUMN_BITMAP = "bitmap";
        public static final String COLUMN_IMAGENAME = "imageName";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_DATE_PURCHASE = "datePurchase";
        public static final String COLUMN_NOTES = "notes";

        public static final String[] ALL_COLUMNS = new String[]{
                COLUMN_SERIAL,
                COLUMN_MODEL_CODE,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_CATEGORY_ID,
                COLUMN_CATEGORY_NAME,
                COLUMN_PRODUCT_CLASS_ID,
                COLUMN_PRODUCT_CLASS_DESCRIPTION,
                COLUMN_SECTOR_ID,
                COLUMN_SECTOR_NAME,
                COLUMN_QUANTITY,
                COLUMN_VALUE,
                COLUMN_VENDOR,
                COLUMN_BITMAP,
                COLUMN_IMAGENAME,
                COLUMN_URL,
                COLUMN_DATE_PURCHASE,
                COLUMN_NOTES
        };

        public static final String PRODUCT_INNER = String.format(
                "%s " +
                        "INNER JOIN %s ON %s.%s = %s.%s " +
                        "INNER JOIN %s ON %s.%s = %s.%s " +
                        "INNER JOIN %s ON %s.%s = %s.%s",
                ProductViewEntry.TABLE_NAME,
                COLUMN_CATEGORY_ID, ProductViewEntry.TABLE_NAME, COLUMN_CATEGORY_ID, CategoryEntry.TABLE_NAME, CategoryEntry._ID,
                COLUMN_PRODUCT_CLASS_ID, ProductViewEntry.TABLE_NAME, COLUMN_PRODUCT_CLASS_ID, ProductClassEntry.TABLE_NAME, ProductClassEntry._ID,
                COLUMN_SECTOR_ID, ProductViewEntry.TABLE_NAME, COLUMN_SECTOR_ID, SectorEntry.TABLE_NAME, SectorEntry._ID
        );
        public static final String DEFAULT_SORT = BaseColumns._ID;

        public static HashMap<String, String> sProductViewProjectionMap;

        static {
            sProductViewProjectionMap = new HashMap<>();
            sProductViewProjectionMap.put(ProductEntry._ID, ProductEntry.TABLE_NAME + "." + BaseColumns._ID);
            sProductViewProjectionMap.put(COLUMN_SERIAL, COLUMN_SERIAL);
            sProductViewProjectionMap.put(COLUMN_MODEL_CODE, COLUMN_MODEL_CODE);
            sProductViewProjectionMap.put(COLUMN_SHORTNAME, ProductEntry.TABLE_NAME + "." + ProductEntry.COLUMN_SHORTNAME);
            sProductViewProjectionMap.put(COLUMN_DESCRIPTION, ProductEntry.TABLE_NAME + "." + ProductEntry.COLUMN_DESCRIPTION);
            sProductViewProjectionMap.put(COLUMN_CATEGORY_ID, CategoryEntry.TABLE_NAME + "." + CategoryEntry._ID);
            sProductViewProjectionMap.put(COLUMN_CATEGORY_NAME, CategoryEntry.TABLE_NAME + "." + CategoryEntry.COLUMN_NAME);
            sProductViewProjectionMap.put(COLUMN_PRODUCT_CLASS_ID, ProductClassEntry.TABLE_NAME + "." + ProductClassEntry._ID);
            sProductViewProjectionMap.put(COLUMN_PRODUCT_CLASS_DESCRIPTION, ProductClassEntry.TABLE_NAME + "." + ProductClassEntry.COLUMN_DESCRIPTION);
            sProductViewProjectionMap.put(COLUMN_SECTOR_ID, SectorEntry.TABLE_NAME + "." + SectorEntry._ID);
            sProductViewProjectionMap.put(COLUMN_SECTOR_NAME, SectorEntry.TABLE_NAME + "." + SectorEntry.COLUMN_NAME);
            sProductViewProjectionMap.put(COLUMN_QUANTITY, COLUMN_QUANTITY);
            sProductViewProjectionMap.put(COLUMN_VALUE, COLUMN_VALUE);
            sProductViewProjectionMap.put(COLUMN_VENDOR, COLUMN_VENDOR);
            sProductViewProjectionMap.put(COLUMN_BITMAP, COLUMN_BITMAP);
            sProductViewProjectionMap.put(COLUMN_IMAGENAME, ProductEntry.TABLE_NAME + "." + ProductEntry.COLUMN_IMAGENAME);
            sProductViewProjectionMap.put(COLUMN_URL, COLUMN_URL);
            sProductViewProjectionMap.put(COLUMN_DATE_PURCHASE, COLUMN_DATE_PURCHASE);
            sProductViewProjectionMap.put(COLUMN_NOTES, COLUMN_NOTES);
        }
    }

    public static class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "product";
        public static final String COLUMN_SERIAL = "serial";
        public static final String COLUMN_MODEL_CODE = "modelCode";
        public static final String COLUMN_SHORTNAME = "shortname";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CATEGORY_ID = "category";
        public static final String COLUMN_PRODUCT_CLASS_ID = "productClass";
        public static final String COLUMN_SECTOR_ID = "sector";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_VENDOR = "vendor";
        public static final String COLUMN_BITMAP = "bitmap";
        public static final String COLUMN_IMAGENAME = "imageName";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_DATE_PURCHASE = "datePurchase";
        public static final String COLUMN_NOTES = "notes";

        public static final String REFERENCES_CATEGORY_ID = String.format(
                "FOREIGN KEY (%s) REFERENCES %s(%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COLUMN_CATEGORY_ID, CategoryEntry.TABLE_NAME, BaseColumns._ID
        );
        public static final String REFERENCES_PRODUCT_CLASS_ID = String.format(
                "FOREIGN KEY (%s) REFERENCES %s(%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COLUMN_PRODUCT_CLASS_ID, ProductClassEntry.TABLE_NAME, BaseColumns._ID
        );
        public static final String REFERENCES_SECTOR_ID = String.format(
                "FOREIGN KEY (%s) REFERENCES %s(%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                COLUMN_SECTOR_ID, SectorEntry.TABLE_NAME, BaseColumns._ID
        );

        public static final String WHERE_ID = String.format(
                "%s = ?",
                BaseColumns._ID
        );

        //En el mismo orden que las declaramos
        public static final String[] ALL_COLUMNS = new String[]{
                BaseColumns._ID,
                COLUMN_SERIAL,
                COLUMN_MODEL_CODE,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_CATEGORY_ID,
                COLUMN_PRODUCT_CLASS_ID,
                COLUMN_SECTOR_ID,
                COLUMN_QUANTITY,
                COLUMN_VALUE,
                COLUMN_VENDOR,
                COLUMN_BITMAP,
                COLUMN_IMAGENAME,
                COLUMN_URL,
                COLUMN_DATE_PURCHASE,
                COLUMN_NOTES
        };

        public static final String DEFAULT_SORT = COLUMN_SERIAL;

        public static final String SQL_CREATE_ENTRIES = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s REAL NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s, " +
                        "%s, " +
                        "%s" +
                        ")",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_SERIAL,
                COLUMN_MODEL_CODE,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_CATEGORY_ID,
                COLUMN_PRODUCT_CLASS_ID,
                COLUMN_SECTOR_ID,
                COLUMN_QUANTITY,
                COLUMN_VALUE,
                COLUMN_VENDOR,
                COLUMN_BITMAP,
                COLUMN_IMAGENAME,
                COLUMN_URL,
                COLUMN_DATE_PURCHASE,
                COLUMN_NOTES,
                REFERENCES_CATEGORY_ID,
                REFERENCES_PRODUCT_CLASS_ID,
                REFERENCES_SECTOR_ID
        );

        public static final String SQL_DELETE_ENTRIES = String.format(
                "DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES ", TABLE_NAME,
                COLUMN_SERIAL,
                COLUMN_MODEL_CODE,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_CATEGORY_ID,
                COLUMN_PRODUCT_CLASS_ID,
                COLUMN_SECTOR_ID,
                COLUMN_QUANTITY,
                COLUMN_VALUE,
                COLUMN_VENDOR,
                COLUMN_BITMAP,
                COLUMN_IMAGENAME,
                COLUMN_URL,
                COLUMN_DATE_PURCHASE,
                COLUMN_NOTES
        ) + String.format("('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, '%s', %s, '%s', '%s', '%s', '%s'),",
                "PC_01",
                "ASUS MM 2020",
                "ASUS Muy Malo 2020",
                "No tiene ni tarjeta de red",
                1,
                1,
                1,
                12,
                666.66f,
                "ASUSTEK",
                -1,
                "Logo HP",
                "www.hotmail.com",
                "1995-12-12",
                "¿Este? para Enrique >:D"
        ) + String.format("('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, '%s', %s, '%s', '%s', '%s', '%s'),",
                "PC_02",
                "ASUS MM 2020",
                "ASUS Muy Malo 2020",
                "No tiene ni tarjeta de red",
                1,
                1,
                1,
                1,
                666.66f,
                "ASUSTEK",
                -1,
                "Logo HP",
                "www.gmail.com",
                "1995-12-12",
                "¿Este? para Jaime >:D"
        ) + String.format("('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, '%s', %s, '%s', '%s', '%s', '%s'),",
                "PC_03",
                "ASUS MM 2020",
                "ASUS Muy Malo 2020",
                "Le falla la pantalla",
                1,
                1,
                1,
                1,
                666.66f,
                "ASUSTEK",
                -1,
                "Logo HP",
                "www.gmail.com",
                "1995-12-12",
                "¿Este? para Pablo >:D"
        ) + String.format("('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, '%s', %s, '%s', '%s', '%s', '%s'),",
                "Monitor ACER",
                "ACER 256PX",
                "ASUS con 256 píxeles",
                "Sólo se ven dos colores",
                2,
                2,
                2,
                12,
                166.66f,
                "ASUSTEK2",
                -1,
                "Logo HP",
                "www.gmail.com",
                "1995-12-12",
                "¿Este? para Nico >:D"
        ) + String.format("('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, '%s', %s, '%s', '%s', '%s', '%s'),",
                "CPU_01",
                "Tontel 16Hz",
                "CPU Tontel de 16Hz",
                "Con overclock se queda en 17Hz",
                2,
                2,
                2,
                12,
                1066.66f,
                "ASUSTEK3",
                -1,
                "Logo Tontel",
                "www.tontel.co.nz",
                "2995-12-12",
                "¿Este? para Salva >:D"
        ) + String.format("('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, '%s', %s, '%s', '%s', '%s', '%s')",
                "Ventilador_01",
                "RSP-6000",
                "Resoplaplús 6000",
                "Especial para los resopladores de 2º",
                2,
                2,
                2,
                15,
                20.00f,
                "ASUSTEK4",
                -1,
                "Logo Indra",
                "www.gmail.com",
                "3995-12-12",
                "¿Este? para todo segundo >:D"
        );

    }

    public static class ProductClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "productClass";
        public static final String COLUMN_DESCRIPTION = "description";


        //En el mismo orden que las declaramos
        public static final String[] ALL_COLUMNS = new String[]{
                BaseColumns._ID,
                COLUMN_DESCRIPTION
        };

        public static final String DEFAULT_SORT = COLUMN_DESCRIPTION;

        public static final String SQL_CREATE_ENTRIES = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_DESCRIPTION
        );

        public static final String SQL_DELETE_ENTRIES = String.format(
                "DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s) VALUES ", TABLE_NAME,
                COLUMN_DESCRIPTION
        ) + String.format("('%s'),",
                "PCses"
        ) + String.format("('%s'),",
                "Monitores"
        ) + String.format("('%s'),",
                "CPUses"
        ) + String.format("('%s')",
                "Ventiladoreses"
        );

    }

    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SHORTNAME = "shortname";
        public static final String COLUMN_DESCRIPTION = "description";

        //En el mismo orden que las declaramos
        public static final String[] ALL_COLUMNS = new String[]{
                BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION
        };

        public static final String DEFAULT_SORT = COLUMN_NAME;

        public static final String SQL_CREATE_ENTRIES = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL" +
                        ")",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION
        );

        public static final String SQL_DELETE_ENTRIES = String.format(
                "DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_INSERT_ENTRIES = String.format(
                "INSERT INTO %s (%s, %s, %s) VALUES ", TABLE_NAME,
                COLUMN_NAME,
                COLUMN_SHORTNAME,
                COLUMN_DESCRIPTION
        ) + String.format("('%s','%s','%s'),",
                "Hardware para el alumnado",
                "Hardware",
                "Todo tipo de material electrónico excepto ventiladores"
        ) + String.format("('%s','%s','%s'),",
                "Software para el dios de la sangre",
                "Software",
                "Programas para torturar alumnos"
        ) + String.format("('%s','%s','%s')",
                "Huesos para el trono de huesos",
                "Ventiladores",
                "Para que los alumnos soplen"
        );

    }

}
