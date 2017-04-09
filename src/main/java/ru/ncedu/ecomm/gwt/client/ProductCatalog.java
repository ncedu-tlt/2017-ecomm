package ru.ncedu.ecomm.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import ru.ncedu.ecomm.gwt.shared.ProductJSO;

import java.util.Comparator;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProductCatalog implements EntryPoint {

    private static final String JSON_URL = GWT.getHostPageBaseURL() + "rest/ecomm/product";

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        // Create a CellTable.
        CellTable<ProductJSO> table = new CellTable<>();

        // Create id column.
        TextColumn<ProductJSO> idColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getId());
            }
        };
        // Make the id column sortable.
        idColumn.setSortable(true);

        // Create name column.
        TextColumn<ProductJSO> nameColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return product.getName();
            }
        };
        // Make the name column sortable.
        nameColumn.setSortable(true);

        // Create price column.
        TextColumn<ProductJSO> priceColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getPrice());
            }
        };

        // Create category column.
        TextColumn<ProductJSO> categoryColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getCategoryId());
            }
        };

        // Create description column.
        TextColumn<ProductJSO> descriptionColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return product.getDescription();
            }
        };

        // Add the columns.
        table.addColumn(idColumn, "ID");
        table.addColumn(nameColumn, "Name");
        table.addColumn(priceColumn, "Price");
        table.addColumn(categoryColumn, "Category");
        table.addColumn(descriptionColumn, "Description");

        // Create a data provider.
        ListDataProvider<ProductJSO> dataProvider = new ListDataProvider<>();

        // Connect the table to the data provider.
        dataProvider.addDataDisplay(table);

        List<ProductJSO> list = dataProvider.getList();

        // Send request to server and catch any errors.
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL);
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        JsArray<ProductJSO> products = JsonUtils.safeEval(response.getText());

                        // Add the data to the data provider, which automatically pushes it to the widget.

                        for (int i = 0; i < products.length(); i++) {
                            list.add(products.get(i));
                        }
                    } else {
                        Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't retrieve JSON (" + e.getMessage() + ")");
        }

        // Add a ColumnSortEvent.ListHandler to connect sorting to the java.util.List.
        ColumnSortEvent.ListHandler<ProductJSO> idColumnSortHandler = new ColumnSortEvent.ListHandler<ProductJSO>(
                list);
        idColumnSortHandler.setComparator(idColumn,
                (o1, o2) -> {
                    if (o1 == o2) {
                        return 0;
                    }

                    // Compare the name columns.
                    if (o1 != null) {
                        return (o2 != null) ? Double.compare(o1.getId(), o2.getId()) : 1;
                    }
                    return -1;
                });
        table.addColumnSortHandler(idColumnSortHandler);

        // Add a ColumnSortEvent.ListHandler to connect sorting to the java.util.List.
        ColumnSortEvent.ListHandler<ProductJSO> columnSortHandler = new ColumnSortEvent.ListHandler<ProductJSO>(
                list);
        columnSortHandler.setComparator(nameColumn,
                (o1, o2) -> {
                    if (o1 == o2) {
                        return 0;
                    }

                    // Compare the name columns.
                    if (o1 != null) {
                        return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
                    }
                    return -1;
                });
        table.addColumnSortHandler(columnSortHandler);

        // We know that the data is sorted alphabetically by default.
        table.getColumnSortList().push(idColumn);

        // Add it to the root panel.
        RootPanel.get("productTable").add(table);
    }
}
