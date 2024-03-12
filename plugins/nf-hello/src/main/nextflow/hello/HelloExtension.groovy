package nextflow.hello


import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import groovyx.gpars.dataflow.DataflowReadChannel
import groovyx.gpars.dataflow.DataflowWriteChannel
import nextflow.Channel
import nextflow.Session
import nextflow.extension.CH
import nextflow.extension.DataflowHelper
import nextflow.plugin.extension.Factory
import nextflow.plugin.extension.Function
import nextflow.plugin.extension.Operator
import nextflow.plugin.extension.PluginExtensionPoint

/**
 * Example plugin extension showing how to implement a basic
 * channel factory method, a channel operator and a custom function.
 *
 * @author : maxulysse <maxime.garcia@seqera.io>
 *
 */
@Slf4j
@CompileStatic
class HelloExtension extends PluginExtensionPoint {

    /*
     * A session hold information about current execution of the script
     */
    private Session session

    /*
     * nf-core initializes the plugin once loaded and session is ready
     * @param session
     */
    @Override
    protected void init(Session session) {
        this.session = session
    }

    /*
     * Get the value from the key attribute within the params.genomes[genome] map
     */
    @Function
    String getGenomeAttribute(String attribute) {
        def Map genomes = (Map) session.params.genomes
        def String genome = (String) session.params.genome

        def Boolean keyExists = (genomes && genome && genomes.containsKey(genome)) ? true : false

        return keyExists ? genomes[ genome ][ attribute ] : null
    }

}
