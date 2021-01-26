<template>
    <v-card class="mx-auto my-12" max-width="320">
        <v-img height="180" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></v-img>

        <v-card-title>지허니 100대 때리기</v-card-title>

        <v-card-actions>
            <v-btn color="deep-purple lighten-2" text="text" @click="reserve">
                서와닝
            </v-btn>
            <v-spacer/>
            <div>
                #운동
            </div>

        </v-card-actions>

        <v-divider class="mx-4"></v-divider>
        <v-card-actions>
            <v-btn color="deep-purple lighten-2" text="text" @click="detail">
                자세히보기
            </v-btn>
            <v-spacer/>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon"
                color="purple lighten-2">
                <v-icon>mdi-cards-heart</v-icon>
            </v-btn>
            <span>0</span>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon"
                color="purple lighten-2">
                <v-icon>mdi-comment-outline</v-icon>
            </v-btn>
            <span>0</span>
            <v-btn
                small="small"
                class="ma-2"
                text="text"
                icon="icon"
                color="purple lighten-2">
                <v-icon>mdi-bookmark</v-icon>
            </v-btn>
            <span>0</span>
        </v-card-actions>
        <v-dialog v-model="loading" max-width="1000">
            <v-card>
                 <v-card-title>지허니 100대 때리기</v-card-title>
            </v-card>


        </v-dialog>
    </v-card>
</template>

<script>
    export default {
        data: () => ({loading: false, selection: 1}),

        methods: {
            detail() {
                this.loading = true


            }
        }
    }
</script>

<style>
    .data {
        text-align: left;
    }
</style>