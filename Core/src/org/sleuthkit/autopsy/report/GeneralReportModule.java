/*
 * Autopsy Forensic Browser
 *
 * Copyright 2012-2020 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.report;

public interface GeneralReportModule extends ReportModule {

    /**
     * Called to generate the report. Method is responsible for saving the file
     * at the path specified and updating progress via the progressPanel object.
     *
     * @param baseReportDir Base directory that reports are being stored in.
     * Report should go into baseReportDir + getRelativeFilePath().
     * @param progressPanel panel to update the report's progress with
     */
    public void generateReport(String baseReportDir, ReportProgressPanel progressPanel);

    /**
     * Determines if the module supports report generation on a subset of data
     * sources in a case. Defaults to false. Modules that override this to true
     * should implement all generateReport overloads. The data source selections
     * are stored in the GeneralReportSettings instance.
     *
     * @return True if the module can be configured to run on a subset of data
     * sources.
     */
    default boolean supportsDataSourceSelection() {
        return false;
    }

    /**
     * Called to generate the report. Method is responsible for saving the file
     * and updating progress via the progressPanel object. Configuration
     * parameters are passed in the settings class, most notably the directory
     * to save the report. Modules should try to respond to all configuration
     * parameters.
     *
     * @param settings Configuration parameters to customize the report
     * generation process
     * @param progressPanel panel to update the report's progress with
     */
    default void generateReport(GeneralReportSettings settings, ReportProgressPanel progressPanel) {
        generateReport(settings.getReportDirectoryPath(), progressPanel);
    }
}
