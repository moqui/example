/*
 * This software is in the public domain under CC0 1.0 Universal plus a
 * Grant of Patent License.
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software to the
 * public domain worldwide. This software is distributed without any
 * warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software (see the LICENSE.md file). If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

import org.moqui.Moqui
import org.moqui.context.ExecutionContext
import org.moqui.screen.ScreenTest
import org.moqui.screen.ScreenTest.ScreenTestRender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class SystemScreenRenderTests extends Specification {
    protected final static Logger logger = LoggerFactory.getLogger(SystemScreenRenderTests.class)

    @Shared
    ExecutionContext ec
    @Shared
    ScreenTest screenTest

    def setupSpec() {
        ec = Moqui.getExecutionContext()
        ec.user.loginUser("john.doe", "moqui")
        screenTest = ec.screen.makeTest().baseScreenPath("apps/system")
    }

    def cleanupSpec() {
        long totalTime = System.currentTimeMillis() - screenTest.startTime
        logger.info("Rendered ${screenTest.renderCount} screens (${screenTest.errorCount} errors) in ${ec.l10n.format(totalTime/1000, "0.000")}s, output ${ec.l10n.format(screenTest.renderTotalChars/1000, "#,##0")}k chars")

        ec.destroy()
    }

    def setup() {
        ec.artifactExecution.disableAuthz()
    }

    def cleanup() {
        ec.artifactExecution.enableAuthz()
    }

    @Unroll
    def "render system screen #screenPath (#containsText1, #containsText2)"() {
        setup:
        ScreenTestRender str = screenTest.render(screenPath, [lastStandalone:"-2"], null)
        // logger.info("Rendered ${screenPath} in ${str.getRenderTime()}ms")
        boolean contains1 = containsText1 ? str.assertContains(containsText1) : true
        boolean contains2 = containsText2 ? str.assertContains(containsText2) : true
        if (!contains1) logger.info("In ${screenPath} text 1 [${containsText1}] not found:\n${str.output}")
        if (!contains2) logger.info("In ${screenPath} text 2 [${containsText2}] not found:\n${str.output}")

        expect:
        !str.errorMessages
        contains1
        contains2

        where:
        screenPath | containsText1 | containsText2

        // AuditLog screen
        // "AuditLog?changedEntityName=example&changedEntityName_op=contains" | "statusId" | "EXST_IN_DESIGN"

        // DataDocument screens
        // NOTE: nothing specific to test in DataDocument screens unless at least mantle is in place
        // TODO: add example DataDocument and use here; also needs dependency on moqui-elasticsearch for these
        // "DataDocument/Search" | "" | ""
        // "DataDocument/Index" | "" | ""
        // "DataDocument/Export" | "" | ""

        // EntitySync screens
        "EntitySync/EntitySyncList" | "Example sync" | ""
        "EntitySync/EntitySyncDetail?entitySyncId=EXAMPLE" | "john.doe" | "moqui.example.ExampleItem"

        // SystemMessage screens
        // send a message using Tools/Service/ServiceRun (note that this does not work as an external URL, gets caught by security stuff)
        "../tools/Service/ServiceRun/run?serviceName=moqui.example.ExampleServices.produce#ExampleMessage&systemMessageRemoteId=Example1Direct&exampleId=TEST1" | "" | ""
        "SystemMessage/Message/SystemMessageList" | "Example Message" | "Example1Local"
        "SystemMessage/Message/SystemMessageDetail?systemMessageId=100000" | "Sent" | "100001"
        "SystemMessage/Message/SystemMessageDetail/EditMessageText?systemMessageId=100000" |
                "Test Example Name" | "EXST_IN_DESIGN"
        "SystemMessage/Remote/MessageRemoteList" | "Example Local" | "john.doe"
        "SystemMessage/Remote/MessageRemoteDetail?systemMessageRemoteId=Example1Local" |
                "Example Local" | "http://localhost:8080/rest/sm"
        "SystemMessage/Type/MessageTypeList" | "Example Message" |
                "moqui.example.ExampleServices.consume#ExampleMessage"
        "SystemMessage/Type/MessageTypeDetail?systemMessageTypeId=ExampleMessage" |
                "Example Message" | "moqui.example.ExampleServices.produce#ExampleMessage"

        // Visit screens
        "Visit/VisitList" | "john.doe" | "apps/system/SystemMessage"
        "Visit/VisitDetail?visitId=EX_VISIT" | "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1)" |
                "component://tools/screen/Tools/Service/ServiceRun.xml#run"
    }
}
