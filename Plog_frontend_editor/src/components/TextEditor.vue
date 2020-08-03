<template>
    <editor v-model="editorText" height="500px"/>

</template>

<script>
import 'tui-editor/dist/tui-editor.css'
import 'tui-editor/dist/tui-editor-contents.css'
import 'codemirror/lib/codemirror.css'
import { Editor } from '@toast-ui/vue-editor'

export default { 
    name: 'TextEditor',
    data(){
        return {
            editorText: ''
        }
    },
    components: {
        'editor': Editor
    }
}
</script>