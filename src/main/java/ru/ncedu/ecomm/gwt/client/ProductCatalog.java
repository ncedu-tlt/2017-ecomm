package ru.ncedu.ecomm.gwt.client;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import ru.ncedu.ecomm.gwt.shared.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.gwt.view.client.DefaultSelectionEventManager.createCheckboxManager;

public class ProductCatalog implements EntryPoint {
    private static final String JSON_URL_CATEGORIES = GWT.getHostPageBaseURL() + "rest/ecomm/categories";
    private static final String JSON_URL_ALL_PRODUCTS = GWT.getHostPageBaseURL() + "rest/ecomm/product";
    private static final String JSON_URL_CHILDREN_PRODUCTS = GWT.getHostPageBaseURL() + "rest/ecomm/v2/product/childrenProducts/";
    private static final String DELETE_PRODUCT  = GWT.getHostPageBaseURL() + "rest/ecomm/product/";
    private static final String JSON_URL_ALL_DISCOUNTS  = GWT.getHostPageBaseURL() + "rest/ecomm/discounts";
    private static final int SUCCESS_ANSWER = 200;
    private static final int NULL_PARENT = 0;
    private static final int ALL_CATEGORIES = 0;
    private static final String MAIN_DIV = "productTable";
    private final ListDataProvider<ProductJSO> dataProviderProducts = new ListDataProvider<>();
    private final SingleSelectionModel<ProductJSO> selectionModel = new SingleSelectionModel<>(dataProviderProducts);
    private Button menuButton = new Button("Menu", (ClickHandler) clickEvent -> {

    });
    private int idSelectedCategory;

    @Override
    public void onModuleLoad() {
        createMainMenu();
    }

    private void createMainMenu() {
        ListHandler<ProductJSO> sortHandler = new ListHandler<>(dataProviderProducts.getList());
        CellTable<ProductJSO> productTable = createTable(selectionModel, sortHandler);

        Tree tree = createTree();

        Label productListLabel = new Label("Product List");

        Button editCategoryButton = new Button("Go to edit category", (ClickHandler) clickEvent -> {

        });
        Button addButton = new Button("ADD", (ClickHandler) clickEvent -> {
        });
        Button updateButton = new Button("UPDATE", (ClickHandler) clickEvent -> {
            FormPanel formPanel = updateProductDetailForm();
            RootPanel.get(MAIN_DIV).clear();
            RootPanel.get(MAIN_DIV).add(formPanel);
        });
        Button deleteButton = new Button("DELETE", (ClickHandler) clickEvent -> {
            if (selectionModel.getSelectedObject() != null) {
                deleteProduct(selectionModel.getSelectedObject());
                // Вопрос
                dataProviderProducts.getList().clear();
                if (idSelectedCategory == 0) {
                    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_ALL_PRODUCTS);

                    fillList(dataProviderProducts.getList(), builder);
                }
                else {
                    updateProductDataProvider(idSelectedCategory);
                }
            }
            else Window.alert("Please choose the product");
        });

        HorizontalPanel hpButtons = new HorizontalPanel();
        HorizontalPanel hpManageButtons = new HorizontalPanel();
        HorizontalPanel hpTree = new HorizontalPanel();
        VerticalPanel mainPanel = new VerticalPanel();

        hpButtons.setWidth("1800px");
        hpButtons.setBorderWidth(2);
        hpButtons.add(menuButton);
        hpButtons.add(editCategoryButton);
        hpButtons.add(productListLabel);
        hpButtons.setCellWidth(menuButton, "50px");

        hpManageButtons.add(addButton);
        hpManageButtons.add(updateButton);
        hpManageButtons.add(deleteButton);

        hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        hpButtons.add(hpManageButtons);

        hpTree.setSpacing(50);
        hpTree.add(tree);
        hpTree.add(productTable);

        mainPanel.add(hpButtons);
        mainPanel.add(hpTree);

        RootPanel.get(MAIN_DIV).add(mainPanel);
    }

    private Tree createTree() {
        ListDataProvider<CategoryJSO> dataProvider = new ListDataProvider<>();
        List<CategoryJSO> list = dataProvider.getList();
        Tree tree = new Tree();

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_CATEGORIES);
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        JsArray<CategoryJSO> categories = JsonUtils.safeEval(response.getText());

                        for (int i = 0; i < categories.length(); i++) {
                            list.add(categories.get(i));
                        }

                        TreeItem mainRoot = new TreeItem();
                        mainRoot.setText("All Categories");
                        mainRoot.getElement().setId(String.valueOf(ALL_CATEGORIES));
                        tree.addItem(mainRoot);
                        list.stream().filter(c -> c.getParentId() == NULL_PARENT).forEach(c -> {
                            TreeItem item = new TreeItem();
                            item.setText(c.getName());
                            item.getElement().setId(String.valueOf(c.getCategoryId()));
                            mainRoot.addItem(item);
                            list.stream().filter(c1 -> c1.getParentId() == c.getCategoryId()).forEach(c1 -> {
                                TreeItem childItem = new TreeItem();
                                childItem.setText(c1.getName());
                                item.addItem(childItem);
                                childItem.getElement().setId(String.valueOf(c1.getCategoryId()));
                            });
                            tree.addItem(mainRoot);
                        });

                        tree.addSelectionHandler(selectionEvent -> {
                            TreeItem item = selectionEvent.getSelectedItem();
                            idSelectedCategory = Integer.parseInt(item.getElement().getId());
                            dataProviderProducts.getList().clear();
                            if (Integer.parseInt(item.getElement().getId()) == 0) {
                                RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_ALL_PRODUCTS);

                                fillList(dataProviderProducts.getList(), builder);
                            }
                            else updateProductDataProvider(Integer.parseInt(item.getElement().getId()));
                        });

                    } else {
                        Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't retrieve JSON (" + e.getMessage() + ")");
        }
        return tree;
    }

    private CellTable<ProductJSO> createTable(final SelectionModel<ProductJSO> selectionModel,
                                              ListHandler<ProductJSO> sortHandler) {
        CellTable<ProductJSO> table = new CellTable<>();
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);

        TextColumn<ProductJSO> idColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getProductId());
            }
        };
        idColumn.setSortable(true);
        TextColumn<ProductJSO> nameColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return product.getName();
            }
        };
        TextColumn<ProductJSO> priceColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getPrice());
            }
        };
        TextColumn<ProductJSO> descriptionColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return product.getDescription();
            }
        };

        Column<ProductJSO, Boolean> checkColumn = new Column<ProductJSO, Boolean>(
                new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(ProductJSO object) {
                return selectionModel.isSelected(object);
            }
        };

        dataProviderProducts.addDataDisplay(table);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_ALL_PRODUCTS);

        fillList(dataProviderProducts.getList(), builder);

        ListHandler<ProductJSO> idColumnSortHandler = new ListHandler<>(dataProviderProducts.getList());
        idColumnSortHandler.setComparator(idColumn,
                (ProductJSO o1, ProductJSO o2) -> {
                    if (o1 == o2) {
                        return 0;
                    }

                    if (o1 != null) {
                        return (o2 != null) ? Double.compare(o1.getProductId(), o2.getProductId()) : 1;
                    }
                    return -1;
                });
        table.addColumnSortHandler(idColumnSortHandler);
        table.getColumnSortList().push(idColumn);

        table.addColumn(idColumn, "Id");
        table.addColumn(nameColumn, "Name");
        table.addColumn(priceColumn, "Price");
        table.addColumn(descriptionColumn, "Description");
        table.addColumn(checkColumn);

        table.addColumnSortHandler(sortHandler);
        table.setSelectionModel(selectionModel, createCheckboxManager());

        return table;
    }

    private void updateProductDataProvider(final int category) {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_CHILDREN_PRODUCTS + String.valueOf(category));

        fillList(dataProviderProducts.getList(), builder);
    }

    private void fillList(List<ProductJSO> list, RequestBuilder builder) {
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        JsArray<ProductJSO> products = JsonUtils.safeEval(response.getText());

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
    }

    private void deleteProduct(ProductJSO object) {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, DELETE_PRODUCT + object.getProductId());
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't delete product");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        Window.alert("Product have been deleted");
                    } else {
                        Window.alert("Couldn't delete product (" + response.getStatusText() + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't delete product (" + e.getMessage() + ")");
        }
    }

    private FormPanel updateProductDetailForm() {
        FormPanel formPanel = new FormPanel();
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);

        VerticalPanel vpMain = new VerticalPanel();
        vpMain.setSpacing(20);
        formPanel.setWidget(vpMain);

        TextBox category = new TextBox();
        category.setText(String.valueOf(selectionModel.getSelectedObject().getCategoryId()));
        TextBox name = new TextBox();
        name.setText(selectionModel.getSelectedObject().getName());

        HorizontalPanel hpPrice = new HorizontalPanel();
        hpPrice.setSpacing(3);
        TextBox price = new TextBox();
        price.setText(String.valueOf(selectionModel.getSelectedObject().getPrice()));

        ListBox listBox = new ListBox();
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_ALL_DISCOUNTS);
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        JsArray<DiscountJSO> discounts = JsonUtils.safeEval(response.getText());

                        for (int i = 0; i < discounts.length(); i++) {
                            listBox.addItem(discounts.get(i).getName());
                        }

                    } else {
                        Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't retrieve JSON (" + e.getMessage() + ")");
        }

        listBox.setSelectedIndex((int) (selectionModel.getSelectedObject().getDiscountId() - 1));
        hpPrice.add(price);
        hpPrice.add(listBox);

        TextArea description = new TextArea();
        description.setText(selectionModel.getSelectedObject().getDescription());


        FileUpload fileUpload = new FileUpload();
        DecoratorPanel dp = new DecoratorPanel();
        VerticalPanel vp = new VerticalPanel();
        dp.add(vp);

        int sizeGroups = selectionModel.getSelectedObject().getCharacteristicGroups().length();
        for (int i = 0; i < sizeGroups; i++) {
            int sizeCharacteristics = selectionModel.getSelectedObject().getCharacteristicGroups().get(i).getCharacteristics().length();

            Label labelGroup = new Label();
            labelGroup.setText(selectionModel.getSelectedObject().getCharacteristicGroups().get(i).getName());
            labelGroup.setWidth("300px");

            vp.add(labelGroup);
            VerticalPanel vpChars = new VerticalPanel();
            vpChars.setSpacing(3);
            Map<String, TextBox> map = new HashMap<>();
            for (int j = 0; j < sizeCharacteristics; j++) {
                HorizontalPanel hpValues = new HorizontalPanel();
                hpValues.setSpacing(5);

                Label labelChar = new Label();
                labelChar.setText(selectionModel.getSelectedObject().getCharacteristicGroups().get(i).getCharacteristics().get(j).getName());
                TextBox tbValue = new TextBox();
                tbValue.setText(selectionModel.getSelectedObject().getCharacteristicGroups().get(i).getCharacteristics().get(j).getValue());

                //map.put(selectionModel.getSelectedObject().getCharacteristicGroups().get(i).getCharacteristics().get(j)., tbValue);
                hpValues.add(labelChar);
                hpValues.add(tbValue);
                vpChars.add(hpValues);
            }
            vp.add(vpChars);
        }

        HorizontalPanel hpButtons = new HorizontalPanel();
        hpButtons.setSpacing(10);
        Button okButton = new Button("Ok", (ClickHandler) clickEvent -> {
            Window.alert(JsonUtils.stringify(selectionModel.getSelectedObject()));
        });
        Button cancelButton = new Button("Cancel", (ClickHandler) clickEvent -> {
            RootPanel.get(MAIN_DIV).clear();
            createMainMenu();
        });
        hpButtons.add(okButton);
        hpButtons.add(cancelButton);

        vpMain.add(menuButton);
        vpMain.add(category);
        vpMain.add(name);
        vpMain.add(hpPrice);
        vpMain.add(description);
        vpMain.add(fileUpload);
        vpMain.add(dp);
        vpMain.add(hpButtons);

        return formPanel;
    }
}