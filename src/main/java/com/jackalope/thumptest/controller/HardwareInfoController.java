/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.service.HardwareInfoService;
import com.jackalope.thumptest.service.I18nService;
import com.jackalope.thumptest.util.HardwareInfoTableCell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;

import static java.lang.Long.valueOf;

@Slf4j
public class HardwareInfoController {
    private final HardwareInfoService hardwareInfoService;
    private final I18nService i18nService;

    @FXML
    public Label hardwareInfoTitle;

    @FXML
    public TableView hardwareTable;

    public HardwareInfoController() {
        i18nService = new I18nService();
        hardwareInfoService = new HardwareInfoService(i18nService);
    }

    public void initialize() {
        log.info("Initialising Hardware Info UI..");

        populateHardwareTable();

        log.info("Hardware Info UI initialised.");
    }

    @FXML
    protected void closeWindow() {
        Stage stage = (Stage) hardwareTable.getScene().getWindow();
        stage.close();
    }

    private void populateHardwareTable() {
        TableColumn keyColumn = new TableColumn(i18nService.getString("table.key"));
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));

        TableColumn valueColumn = new TableColumn(i18nService.getString("table.value"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        CentralProcessor cpu = hardwareInfoService.getCPUObj();
        SystemInfo systemInfo = hardwareInfoService.getSystemInfoObj();

        double maxFrequencyGHzDouble = valueOf(cpu.getMaxFreq()).doubleValue() / 1000000000;
        String maxFrequencyGHz = HardwareInfoService.twoDecimalPlaces(maxFrequencyGHzDouble);
        double vendorFrequencyGHzDouble = valueOf(cpu.getMaxFreq()).doubleValue() / 1000000000;
        String vendorFrequencyGHz = HardwareInfoService.twoDecimalPlaces(vendorFrequencyGHzDouble);

        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuName"), cpu.getProcessorIdentifier().getName()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuFamily"), cpu.getProcessorIdentifier().getFamily()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuId"), cpu.getProcessorIdentifier().getIdentifier()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuModel"), cpu.getProcessorIdentifier().getModel()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuVendor"), cpu.getProcessorIdentifier().getVendor()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuMicroArch"), cpu.getProcessorIdentifier().getMicroarchitecture()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuPhysCores"), Integer.toString(cpu.getPhysicalProcessorCount())));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuLogiCores"), Integer.toString(cpu.getLogicalProcessorCount())));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuFreq"), vendorFrequencyGHz));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.cpuMaxFreq"), maxFrequencyGHz));

        GraphicsCard gpu = systemInfo.getHardware().getGraphicsCards().getLast();

        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.memory"), Long.toString(systemInfo.getHardware().getMemory().getTotal())));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.gpuId"), gpu.getDeviceId()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.gpuName"), gpu.getName()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.gpuVendor"), gpu.getVendor()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.gpuVersion"), gpu.getVersionInfo()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.gpuVRAM"), Long.toString(gpu.getVRam())));

        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.osManu"), systemInfo.getOperatingSystem().getManufacturer()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.osFamily"), systemInfo.getOperatingSystem().getFamily()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.osVersion"), systemInfo.getOperatingSystem().getVersionInfo().getVersion()));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.bitness"), Integer.toString(systemInfo.getOperatingSystem().getBitness())));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.sysUptime"), Long.toString(systemInfo.getOperatingSystem().getSystemUptime())));
        hardwareTable.getItems().add(new HardwareInfoTableCell(i18nService.getString("table.sysBootTime"), Long.toString(systemInfo.getOperatingSystem().getSystemBootTime())));

        hardwareTable.getColumns().setAll(keyColumn, valueColumn);
    }
}