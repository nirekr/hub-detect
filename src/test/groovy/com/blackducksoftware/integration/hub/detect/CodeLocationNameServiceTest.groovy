/*
 * Copyright (C) 2017 Black Duck Software Inc.
 * http://www.blackducksoftware.com/
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Black Duck Software ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Black Duck Software.
 */
package com.blackducksoftware.integration.hub.detect

import static org.junit.Assert.*

import java.nio.file.Path
import java.nio.file.Paths

import org.junit.Test

import com.blackducksoftware.integration.hub.detect.codelocation.CodeLocationName
import com.blackducksoftware.integration.hub.detect.codelocation.CodeLocationNameProvider
import com.blackducksoftware.integration.hub.detect.codelocation.CodeLocationNameProvider1
import com.blackducksoftware.integration.hub.detect.codelocation.CodeLocationNameProvider2
import com.blackducksoftware.integration.hub.detect.codelocation.CodeLocationNameProvider3
import com.blackducksoftware.integration.hub.detect.codelocation.CodeLocationNameService
import com.blackducksoftware.integration.hub.detect.model.BomToolType

class CodeLocationNameServiceTest {
    def codeLocationNameService = new CodeLocationNameService()

    @Test
    public void testScanVersion1() {
        String expected = 'hub-common-rest/target/hub-common-rest/2.5.1-SNAPSHOT Hub Detect Scan'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider1()
        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String scanTargetPath = '/Users/ekerwin/Documents/source/integration/hub-common-rest/target'
        String projectName = 'hub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createScanName(sourcePath, scanTargetPath, projectName, projectVersionName, prefix, suffix)
        String actual = codeLocationNameProvider.generateScanName(codeLocationName)

        assertEquals(expected, actual)
    }

    @Test
    public void testScanVersion2() {
        String expected = 'hub-common-rest/target/hub-common-rest/2.5.1-SNAPSHOT SCAN'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider2()

        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String scanTargetPath = '/Users/ekerwin/Documents/source/integration/hub-common-rest/target'
        String projectName = 'hub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createScanName(sourcePath, scanTargetPath, projectName, projectVersionName, prefix, suffix)
        String actual = codeLocationNameProvider.generateScanName(codeLocationName)

        assertEquals(expected, actual)
    }

    @Test
    public void testScanVersion3() {
        String expected = 'hub-common-rest/target/hub-common-rest/2.5.1-SNAPSHOT scan'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider3()

        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String scanTargetPath = '/Users/ekerwin/Documents/source/integration/hub-common-rest/target'
        String projectName = 'hub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createScanName(sourcePath, scanTargetPath, projectName, projectVersionName, prefix, suffix)
        String actual = codeLocationNameProvider.generateScanName(codeLocationName)

        assertEquals(expected, actual)
    }

    @Test
    public void testBomVersion1() {
        String expected = 'NPM/hub-common-rest/hub-common-rest/2.5.1-SNAPSHOT Hub Detect Tool'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider1()

        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String projectName = 'hub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createBomToolName(sourcePath, projectName, projectVersionName, BomToolType.NPM, prefix, suffix)
        String actual = codeLocationNameProvider.generateBomToolName(codeLocationName)

        assertEquals(expected, actual)
    }

    @Test
    public void testBomVersion2() {
        String expected = 'NPM/hub-common-rest/hub-common-rest/2.5.1-SNAPSHOT BOM'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider2()

        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String projectName = 'hub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createBomToolName(sourcePath, projectName, projectVersionName, BomToolType.NPM, prefix, suffix)
        String actual = codeLocationNameProvider.generateBomToolName(codeLocationName)

        assertEquals(expected, actual)
    }

    @Test
    public void testBomVersion3() {
        String expected = 'hub-common-rest/hub-common-rest/2.5.1-SNAPSHOT npm/bom'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider3()

        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String projectName = 'hub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createBomToolName(sourcePath, projectName, projectVersionName, BomToolType.NPM, prefix, suffix)
        String actual = codeLocationNameProvider.generateBomToolName(codeLocationName)

        assertEquals(expected, actual)
    }

    @Test
    public void testLongCodeLocationNames() {
        String expected = 'hub-common-rest/hub-common-resthub-...esthub-common-rest/2.5.1-SNAPSHOT npm/bom'

        CodeLocationNameProvider codeLocationNameProvider = new CodeLocationNameProvider3()

        Path sourcePath = Paths.get('/Users/ekerwin/Documents/source/integration/hub-common-rest')
        String projectName = 'hub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-resthub-common-rest'
        String projectVersionName = '2.5.1-SNAPSHOT'
        String prefix = ''
        String suffix = ''
        CodeLocationName codeLocationName = codeLocationNameService.createBomToolName(sourcePath, projectName, projectVersionName, BomToolType.NPM, prefix, suffix)
        String actual = codeLocationNameProvider.generateBomToolName(codeLocationName)

        assertEquals(expected, actual)
    }
}
